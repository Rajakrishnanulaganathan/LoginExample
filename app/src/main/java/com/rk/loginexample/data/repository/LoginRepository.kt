package com.rk.loginexample.data.repository

import com.rk.loginexample.data.remote.network.SafeApiRequest
import com.rk.loginexample.data.remote.network.MyApi
import com.rk.loginexample.data.local.database.Appdatabase
import com.rk.loginexample.data.local.model.User
import com.rk.loginexample.data.remote.model.AuthResponse

class LoginRepository (
    private val api: MyApi,
    private val appdatabase: Appdatabase
): SafeApiRequest(){

    suspend fun login(email: String, password: String): AuthResponse {
        return apiRequest{api.login(email, password)}
    }

    suspend fun signup(name:String,email: String, password: String): AuthResponse {
        return apiRequest{api.signup(name,email, password)}
    }

    suspend fun saveUser(user: User): Long {
        return appdatabase.userDao().upsert(user);
    }

    fun getUser() = appdatabase.userDao().getuser()
}