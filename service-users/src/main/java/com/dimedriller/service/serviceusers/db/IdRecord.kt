package com.dimedriller.service.serviceusers.db

import com.dimedriller.service.serviceusers.models.Id

internal data class IdRecord(val name: String, val value: String) {
    constructor(model: Id): this(model.name, model.value)

    fun toModel():Id {
        return Id(name, value)
    }
}