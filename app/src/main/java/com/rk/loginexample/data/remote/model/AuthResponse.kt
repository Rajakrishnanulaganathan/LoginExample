package com.rk.loginexample.data.remote.model

import com.rk.loginexample.data.local.model.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?
)