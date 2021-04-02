package com.dimedriller.service.serviceusers.models

data class Picture(
        val large: String,
        val medium: String,
        val thumbnail: String
) {
    constructor(): this("", "", "")
}