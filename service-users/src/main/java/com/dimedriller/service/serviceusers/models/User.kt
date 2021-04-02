package com.dimedriller.service.serviceusers.models

data class User(
        val id: Id,
        val name: Name,
        val email: String,
        val picture: Picture
)