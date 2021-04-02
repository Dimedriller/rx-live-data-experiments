package com.dimedriller.service.serviceusers.json


import com.google.gson.annotations.SerializedName
import com.dimedriller.service.serviceusers.models.Id

internal class IdElement(
        @SerializedName("name") private val name: String?,
        @SerializedName("value") private val value: String?
) {
    fun toModel(): Id {
        if (name == null
                || value == null) {
            throw IllegalStateException()
        } else {
            return Id(name, value)
        }
    }
}