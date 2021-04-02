package com.dimedriller.service.serviceusers.json

import com.google.gson.annotations.SerializedName
import com.dimedriller.service.serviceusers.models.User
import com.dimedriller.utils.collections.mapIgnoreErr

internal class UsersResponse (
    @SerializedName("results") private val results: List<UserElement>?
) {
    fun parseUsers(): List<User> {
        val usersRaw = results ?: return emptyList()
        return usersRaw.mapIgnoreErr { it.toModel() }
    }
}