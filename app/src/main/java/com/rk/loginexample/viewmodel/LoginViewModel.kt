package com.rk.loginexample.viewmodel

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.rk.loginexample.listner.AuthListner
import com.rk.loginexample.ui.SignUpActivity
import com.rk.loginexample.data.repository.LoginRepository
import com.rk.loginexample.utility.ApiException
import com.rk.loginexample.utility.Coroutines
import com.rk.loginexample.utility.NoInternetException

class LoginViewModel(private val loginRepository: LoginRepository) :ViewModel(){

    var email:String?=null
    var password:String?= null
    var name:String?= null
    var confirmpassword:String?= null

    var authListner: AuthListner?=null

    fun getLoggedInUser() = loginRepository.getUser()


    fun loginclicked(view: View){
        authListner?.onstarted()

        if(email.isNullOrEmpty()||password.isNullOrEmpty()){
            authListner?.onFailure("invalid password or email")
        }
        Coroutines.main {
            try{
            val authResponse =loginRepository.login(email!!,password!!)
            authResponse.user?.let {
                authListner?.onScuccess(it)
                loginRepository.saveUser(it)
                return@main
            }
            authListner?.onFailure(authResponse.message!!)

            }
            catch (e:java.lang.Exception){
                authListner?.onFailure(e.toString())
            }
            catch (e: NoInternetException){
                authListner?.onFailure(e.message!!)
            }

        }

}

    fun onSignup(view: View){
        Intent(view.context, SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


    fun onSignupButtonClick(view: View){
        authListner?.onstarted()

        if(name.isNullOrEmpty()){
            authListner?.onFailure("Name is required")
            return
        }

        if(email.isNullOrEmpty()){
            authListner?.onFailure("Email is required")
            return
        }

        if(password.isNullOrEmpty()){
            authListner?.onFailure("Please enter a password")
            return
        }

        if(password != confirmpassword){
            authListner?.onFailure("Password did not match")
            return
        }


        Coroutines.main {
            try {
                val authResponse = loginRepository.signup(name!!, email!!, password!!)
                authResponse.user?.let {
                    authListner?.onScuccess(it)
                    loginRepository.saveUser(it)
                    return@main
                }
                authListner?.onFailure(authResponse.message!!)
            }catch(e: ApiException){
                authListner?.onFailure(e.message!!)
            }catch (e: NoInternetException){
                authListner?.onFailure(e.message!!)
            }
        }

    }

}