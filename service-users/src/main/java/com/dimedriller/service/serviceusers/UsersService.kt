package com.dimedriller.service.serviceusers

import com.dimedriller.service.serviceusers.models.User
import io.reactivex.Single

interface UsersService {
    fun getUsers(): Single<List<User>>

    fun refreshUsers(): Single<List<User>>
}