package com.dimedriller.service.serviceusers.db

import com.dimedriller.service.serviceusers.models.Name

internal data class NameRecord(
        val first: String,
        val last: String,
        val title: String) {
    constructor(model: Name): this(model.first, model.last, model.title)

    fun toModel(): Name {
        return Name(first, last, title)
    }
}