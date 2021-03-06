package com.rk.loginexample.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rk.loginexample.data.repository.LoginRepository
import com.rk.loginexample.viewmodel.LoginViewModel

class LoginViewModelFactory (private val loginRepository: LoginRepository):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(loginRepository) as T
    }
}