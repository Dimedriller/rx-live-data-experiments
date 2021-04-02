package com.dimedriller.service.serviceusers.json

import com.google.gson.annotations.SerializedName
import com.dimedriller.service.serviceusers.models.Picture
import com.dimedriller.service.serviceusers.models.User

/**
 * This class is in charge for parsing JSON and converting that into model class instance.
 * Parsing logic is intentionally extracted into dedicated class. That allows to make changes related to REST API not
 * affecting model class.
 */
internal class UserElement(
        @SerializedName("email") private val email: String?,
        @SerializedName("id") private val id: IdElement?,
        @SerializedName("name") private val name: NameElement?,
        @SerializedName("picture") private val picture: PictureElement?
) {
    fun toModel(): User {
        // It is not guaranteed all elements are returned by REST API. At the same time for proper usage of this class
        // by UI those fields may be required.
        if (id == null
                || name == null
                || email == null) {
            throw IllegalStateException()
        }

        val id = id.toModel()
        val name = name.toModel()
        val picture = picture?.toModel() ?: Picture()
        return User(id, name, email, picture)
    }
}