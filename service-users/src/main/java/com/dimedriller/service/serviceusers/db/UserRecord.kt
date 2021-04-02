package com.dimedriller.service.serviceusers.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dimedriller.service.serviceusers.models.User

@Entity(tableName = "users")
internal data class UserRecord(
        @PrimaryKey(autoGenerate = false) val key: String,
        @Embedded val id: IdRecord,
        @Embedded val name: NameRecord,
        val email: String,
        @Embedded val picture: PictureRecord
) {
    constructor(model: User)
            : this(model.id.value, IdRecord(model.id), NameRecord(model.name), model.email, PictureRecord(model.picture))

    fun toModel(): User {
        val id = id.toModel()
        val name = name.toModel()
        val picture = picture.toModel()
        return User(id, name, email, picture)
    }
}