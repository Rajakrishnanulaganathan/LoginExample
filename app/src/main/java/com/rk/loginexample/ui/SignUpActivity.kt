package com.rk.loginexample.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rk.loginexample.viewmodel.viewmodelfactory.LoginViewModelFactory
import com.rk.loginexample.R
import com.rk.loginexample.data.local.model.User
import com.rk.loginexample.databinding.ActivitySignUpBinding
import com.rk.loginexample.listner.AuthListner
import com.rk.loginexample.utility.showToast
import com.rk.loginexample.viewmodel.LoginViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity() , KodeinAware,
    AuthListner {
    override val kodein by kodein()
    private val factory : LoginViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var activtybinding :ActivitySignUpBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_sign_up
        )
        var viewModel= ViewModelProviders.of(this,factory).get(LoginViewModel::class.java)
        activtybinding.loginViewModel=viewModel;
        viewModel.authListner=this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if(user != null){
                Intent(this, DashBoardActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onstarted() {
      showToast(this,"Started")
    }

    override fun onScuccess(user: User) {
        showToast(this,"Success${user.email}")

    }

    override fun onFailure(s: String) {
        showToast(this,s)

    }
}
