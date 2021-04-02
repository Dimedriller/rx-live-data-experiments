package com.dimedriller.service.serviceusers

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module
internal class UsersModule {
    @Provides
    @Reusable
    fun usersRestApi(retrofit: Retrofit): UsersRestApi {
        return retrofit.create(UsersRestApi::class.java)
    }

    @Provides
    @Reusable
    fun usersDb(context: Context): UsersDatabase {
        return Room.databaseBuilder(context, UsersDatabase::class.java, UsersDatabase.DB_NAME)
                .build()
    }

    @Provides
    fun usersDao(db: UsersDatabase): UsersDao {
        return db.usersDao()
    }

    @Provides
    fun usersService(serviceImpl: UsersServiceImpl): UsersService {
        return serviceImpl
    }
}