package com.dimedriller.service.serviceusers

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.dimedriller.service.serviceusers.db.UserRecord
import com.dimedriller.service.serviceusers.json.*
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import java.io.IOException

class UsersServiceImplTest {
    private lateinit var restApi: UsersRestApi
    private lateinit var dao: UsersDao
    private lateinit var serviceImpl: UsersServiceImpl

    @Before
    fun setUp() {
        restApi = mock()
        dao = mock()
        serviceImpl = UsersServiceImpl(restApi, dao)

        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    private fun createResponse(count: Int): UsersResponse {
        val usersRaw = ArrayList<UserElement>(count)
        for(index in 0 until count) {
            val idRaw = IdElement("id", index.toString())
            val nameRaw = NameElement("J$index", "K$index", "Mr")
            val pictureRaw = PictureElement("", "", "")
            val userRaw = UserElement("anemail$index@a.com", idRaw, nameRaw, pictureRaw)
            usersRaw.add(userRaw)
        }
        return UsersResponse(usersRaw)
    }

    private fun createCache(count: Int): List<UserRecord> {
        return createResponse(count)
                .parseUsers()
                .map { UserRecord(it) }
    }

    @Test
    fun testGetUsersRemoteSuccess() {
        `when`(restApi.getUsers(any(), any()))
                .thenReturn(Single.just(createResponse(10)))
        `when`(dao.getUsers())
                .thenReturn(Single.just(emptyList()))
        `when`(dao.deleteUsers())
                .thenReturn(Completable.complete())
        `when`(dao.insertUsers(any()))
                .thenReturn(Completable.complete())

        serviceImpl.getUsers()
                .test()
                .assertValue { it.size == 10 }
    }

    @Test
    fun testGetUserRemoteError() {
        `when`(restApi.getUsers(any(), any()))
                .thenReturn(Single.error(IOException()))
        `when`(dao.getUsers())
                .thenReturn(Single.just(emptyList()))

        serviceImpl.getUsers()
                .test()
                .assertError { it is IOException }
    }

    @Test
    fun testGetUsersRemoteSuccessClearingCacheFailed() {
        `when`(restApi.getUsers(any(), any()))
                .thenReturn(Single.just(createResponse(1)))
        `when`(dao.getUsers())
                .thenReturn(Single.just(emptyList()))
        `when`(dao.deleteUsers())
                .thenReturn(Completable.error(IOException()))
        `when`(dao.insertUsers(any()))
                .thenReturn(Completable.complete())

        serviceImpl.getUsers()
                .test()
                .assertValue { it.size == 1 }
    }

    @Test
    fun testGetUsersRemoteSuccessCachingFailed() {
        `when`(restApi.getUsers(any(), any()))
                .thenReturn(Single.just(createResponse(1)))
        `when`(dao.getUsers())
                .thenReturn(Single.just(emptyList()))
        `when`(dao.deleteUsers())
                .thenReturn(Completable.complete())
        `when`(dao.insertUsers(any()))
                .thenReturn(Completable.error(IOException()))

        serviceImpl.getUsers()
                .test()
                .assertValue { it.size == 1 }
    }

    @Test
    fun testGetUsersCached() {
        `when`(restApi.getUsers(any(), any()))
                .thenReturn(Single.error(IOException()))
        `when`(dao.getUsers())
                .thenReturn(Single.just(createCache(13)))

        serviceImpl.getUsers()
                .test()
                .assertValue { it.size == 13 }
    }

    @Test
    fun testGetUsersCacheFailedFetchSuccess() {
        `when`(restApi.getUsers(any(), any()))
                .thenReturn(Single.just(createResponse(4)))
        `when`(dao.getUsers())
                .thenReturn(Single.error(IOException()))
        `when`(dao.deleteUsers())
                .thenReturn(Completable.complete())
        `when`(dao.insertUsers(any()))
                .thenReturn(Completable.complete())

        serviceImpl.getUsers()
                .test()
                .assertValue { it.size == 4 }
    }

    @Test
    fun testRefreshUsersSuccess() {
        `when`(restApi.getUsers(any(), any()))
                .thenReturn(Single.just(createResponse(10)))
        `when`(dao.getUsers())
                .thenReturn(Single.just(createCache(2)))
        `when`(dao.deleteUsers())
                .thenReturn(Completable.complete())
        `when`(dao.insertUsers(any()))
                .thenReturn(Completable.complete())

        serviceImpl.refreshUsers()
                .test()
                .assertValue { it.size == 10 }
    }

    @Test
    fun testRefreshUsersError() {
        `when`(restApi.getUsers(any(), any()))
                .thenReturn(Single.error(IOException()))
        `when`(dao.getUsers())
                .thenReturn(Single.just(createCache(2)))
        `when`(dao.deleteUsers())
                .thenReturn(Completable.complete())
        `when`(dao.insertUsers(any()))
                .thenReturn(Completable.complete())

        serviceImpl.refreshUsers()
                .test()
                .assertError { it is IOException }
    }
}