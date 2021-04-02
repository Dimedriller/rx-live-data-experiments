package com.dimedriller.rxexperiments.usersui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dimedriller.rxexperiments.App
import com.dimedriller.service.serviceusers.UsersComponent

class UsersViewModelFactory(private val application: App)
        : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val usersComponent = application.componentProvider.get(UsersComponent::class.java)
        val usersService = usersComponent.usersService()
        @Suppress("UNCHECKED_CAST")
        return UsersViewModel(usersService) as T
    }
}