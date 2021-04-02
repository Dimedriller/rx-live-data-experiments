package com.dimedriller.service.serviceusers.json

import com.google.gson.annotations.SerializedName
import com.dimedriller.service.serviceusers.models.Picture

internal class PictureElement(
        @SerializedName("large") private val large: String?,
        @SerializedName("medium") private val medium: String?,
        @SerializedName("thumbnail") private val thumbnail: String?
) {
    fun toModel(): Picture {
        val large = large ?: ""
        val medium = medium ?: ""
        val thumbnail = thumbnail ?: ""
        return Picture(large, medium, thumbnail)
    }
}