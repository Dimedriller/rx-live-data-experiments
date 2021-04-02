package com.dimedriller.service.serviceusers

import com.dimedriller.servicecore.ApplicationComponent
import com.dimedriller.servicecore.RestComponent
import dagger.Component

@Component(modules = [UsersModule::class], dependencies = [ApplicationComponent::class, RestComponent::class])
interface UsersComponent {
    fun usersService(): UsersService
}