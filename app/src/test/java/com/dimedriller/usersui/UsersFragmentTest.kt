package com.dimedriller.usersui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.nhaarman.mockitokotlin2.mock
import com.CustomRobolectricRunner
import com.TestActivity
import com.githubstalker.R
import com.dimedriller.rxexperiments.App
import com.dimedriller.rxexperiments.usersui.UsersAdapter
import com.dimedriller.rxexperiments.usersui.UsersFragment
import com.dimedriller.service.serviceusers.UsersComponent
import com.dimedriller.service.serviceusers.UsersService
import com.dimedriller.service.serviceusers.models.Id
import com.dimedriller.service.serviceusers.models.Name
import com.dimedriller.service.serviceusers.models.Picture
import com.dimedriller.service.serviceusers.models.User
import com.dimedriller.utils.component.ComponentRegistry
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.robolectric.Robolectric
import org.robolectric.annotation.Config
import java.io.IOException

@RunWith(CustomRobolectricRunner::class)
@Config(sdk=[28])
class UsersFragmentTest {
    private lateinit var usersService: UsersService
    private lateinit var fragment: UsersFragment

    @Before
    fun setUp() {
        val activity = Robolectric.buildActivity(TestActivity::class.java)
                .setup()
                .get()
        val application = activity.applicationContext as App

        val componentRegistry = application.componentProvider as ComponentRegistry
        val usersComponent = mock<UsersComponent>()
        usersService = mock()
        `when`(usersComponent.usersService()).thenReturn(usersService)
        componentRegistry.register(UsersComponent::class.java, usersComponent)

        fragment = UsersFragment()
    }

    private fun createUsers(count: Int): List<User> {
        val users = ArrayList<User>()
        for(index in 0 until count) {
            val id = Id("id", index.toString())
            val name = Name("first $index", "last $index", "Ms")
            val user = User(id, name, "$index@g.c", Picture())
            users.add(user)
        }
        return users
    }

    @Test
    fun testOnViewCreatedResponseSuccess() {
        `when`(usersService.getUsers())
                .thenReturn(Single.just(createUsers(5)))
        `when`(usersService.refreshUsers())
                .thenReturn(Single.just(createUsers(10)))

        val scenario = launchFragmentInContainer { fragment }
        scenario.moveToState(Lifecycle.State.RESUMED)

        val view = fragment.view!!
        val usersView = view.findViewById<RecyclerView>(R.id.list)
        val usersAdapter = usersView.adapter as UsersAdapter
        assertThat(usersAdapter.itemCount).isEqualTo(5)
    }

    @Test
    fun testOnViewCreatedResponseError() {
        `when`(usersService.getUsers())
                .thenReturn(Single.error(IOException()))

        val scenario = launchFragmentInContainer { fragment }
        scenario.moveToState(Lifecycle.State.RESUMED)
        val usersView = fragment.view!!.findViewById<RecyclerView>(R.id.list)
        val usersAdapter = usersView.adapter as UsersAdapter
        assertThat(usersAdapter.itemCount).isEqualTo(0)
    }
}