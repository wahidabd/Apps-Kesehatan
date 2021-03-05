package com.udacoding.appskesehatan.model.auth

data class UserResponse(
        val data: List<UserItems>? = null,
        val isSuccess: Boolean? = null
)