package com.dimedriller.service.serviceusers.json

import com.google.gson.annotations.SerializedName
import com.dimedriller.service.serviceusers.models.Name

internal class NameElement(
        @SerializedName("first") private val first: String?,
        @SerializedName("last") private val last: String?,
        @SerializedName("title") private val title: String?
) {
    fun toModel(): Name {
        if (first == null
                || last == null) {
            throw IllegalStateException()
        }
        val title = title ?: ""
        return Name(first, last, title)
    }
}