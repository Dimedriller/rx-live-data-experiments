package com.dimedriller.servicecore

import android.app.Application
import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class ApplicationModule(private val application: Application) {
    @Provides
    fun context(): Context {
        return application
    }

    @Provides
    @Reusable
    fun picasso() = Picasso.Builder(application)
            .build()
}