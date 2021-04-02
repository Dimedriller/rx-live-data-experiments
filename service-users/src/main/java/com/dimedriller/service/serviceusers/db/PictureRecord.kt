package com.dimedriller.service.serviceusers.db

import com.dimedriller.service.serviceusers.models.Picture

class PictureRecord(
        val large: String,
        val medium: String,
        val thumbnail: String) {
    constructor(model: Picture): this(model.large, model.medium, model.thumbnail)

    fun toModel(): Picture {
        return Picture(large, medium, thumbnail)
    }
}