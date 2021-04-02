package com.dimedriller.rxexperiments.usersui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.dimedriller.service.serviceusers.UsersService
import com.dimedriller.service.serviceusers.models.User

class UsersViewModel(private val usersService: UsersService): ViewModel() {
    fun getUsers(): LiveData<List<User>> =
            usersService.getUsers()
                    .onErrorReturnItem(emptyList())
                    .toFlowable()
                    .toLiveData()

    fun refreshUsers(): LiveData<List<User>> =
            usersService.refreshUsers()
                    .onErrorReturnItem(emptyList())
                    .toFlowable()
                    .toLiveData()
}