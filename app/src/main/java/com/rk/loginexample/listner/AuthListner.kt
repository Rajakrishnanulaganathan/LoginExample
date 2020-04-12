package com.rk.loginexample.listner

import com.rk.loginexample.data.local.model.User

interface AuthListner {

    fun onstarted()
    fun onScuccess(user: User)
    fun onFailure(s: String)
}