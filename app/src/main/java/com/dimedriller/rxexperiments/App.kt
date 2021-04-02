package com.dimedriller.rxexperiments

import android.app.Application
import com.dimedriller.service.serviceusers.DaggerUsersComponent
import com.dimedriller.service.serviceusers.UsersComponent
import com.dimedriller.servicecore.*
import com.dimedriller.utils.component.ComponentProvider
import com.dimedriller.utils.component.ComponentRegistry

class App : Application() {
    private val componentRegistry = ComponentRegistry()
    val componentProvider: ComponentProvider
        get() = componentRegistry

    override fun onCreate() {
        super.onCreate()

        val applicationModule = ApplicationModule(this)
        val applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(applicationModule)
                .build()
        componentRegistry.register(ApplicationComponent::class.java, applicationComponent)

        val restModule = RestModule(BASE_URL)
        val restComponent = DaggerRestComponent.builder()
                .restModule(restModule)
                .build()
        componentRegistry.register(RestComponent::class.java, restComponent)

        val usersComponent = DaggerUsersComponent.builder()
                .applicationComponent(applicationComponent)
                .restComponent(restComponent)
                .build()
        componentRegistry.register(UsersComponent::class.java, usersComponent)
    }

    companion object {
        const val BASE_URL = "https://randomuser.me"
    }
}