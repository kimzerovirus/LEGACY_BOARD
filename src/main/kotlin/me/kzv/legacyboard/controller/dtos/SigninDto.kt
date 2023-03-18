package me.kzv.legacyboard.controller.dtos

data class SigninRequest(
    val email: String,
    val password: String,
)