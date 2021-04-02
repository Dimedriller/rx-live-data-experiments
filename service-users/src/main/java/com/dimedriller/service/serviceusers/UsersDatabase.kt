package com.dimedriller.service.serviceusers

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dimedriller.service.serviceusers.db.UserRecord

@Database(entities = [UserRecord::class], version = 1, exportSchema = false)
internal abstract class UsersDatabase: RoomDatabase() {
    abstract fun usersDao(): UsersDao

    companion object {
        const val DB_NAME = "UsersDb"
    }
}