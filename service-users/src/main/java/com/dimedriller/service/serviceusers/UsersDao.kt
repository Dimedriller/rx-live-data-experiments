package com.dimedriller.service.serviceusers

import androidx.room.*
import com.dimedriller.service.serviceusers.db.UserRecord
import io.reactivex.Completable
import io.reactivex.Single

@Dao
internal interface UsersDao {
    @Query("SELECT * FROM users")
    fun getUsers(): Single<List<UserRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserRecord>): Completable

    @Query("DELETE FROM users")
    fun deleteUsers(): Completable
}