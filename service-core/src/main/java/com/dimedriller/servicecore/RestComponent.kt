package com.dimedriller.servicecore

import dagger.Component
import retrofit2.Retrofit

@Component(modules = [RestModule::class])
interface RestComponent {
    fun retrofit(): Retrofit
}