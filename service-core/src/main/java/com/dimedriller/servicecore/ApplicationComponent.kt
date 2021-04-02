package com.dimedriller.servicecore

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun context(): Context

    fun picasso(): Picasso
}