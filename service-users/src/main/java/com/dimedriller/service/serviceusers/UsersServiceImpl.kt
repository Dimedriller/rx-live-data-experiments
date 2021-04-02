package com.dimedriller.service.serviceusers

import com.dimedriller.service.serviceusers.db.UserRecord
import com.dimedriller.service.serviceusers.models.User
import dagger.Reusable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@Reusable
internal class UsersServiceImpl @Inject constructor(
        private val restApi: UsersRestApi,
        private val dao: UsersDao)
        : UsersService {
    private fun remoteUsersStream() = restApi.getUsers()
            .map { element -> element.parseUsers() }
            .flatMap {
                val records = it.map { user -> UserRecord(user) }
                return@flatMap dao.deleteUsers()
                        .andThen(dao.insertUsers(records))
                        .onErrorComplete()
                        .andThen(Single.just(it))
            }

    override fun getUsers(): Single<List<User>> {
        return dao.getUsers()
                .onErrorReturnItem(emptyList())
                .subscribeOn(Schedulers.io())
                .flatMap {
                    return@flatMap if (it.isEmpty()) {
                        remoteUsersStream()
                    } else {
                        Single.just(it.map { record -> record.toModel() })
                    }
                }
    }

    override fun refreshUsers(): Single<List<User>> {
        return dao.deleteUsers()
                .subscribeOn(Schedulers.io())
                .onErrorComplete()
                .andThen(Single.defer{ remoteUsersStream() })
    }
}