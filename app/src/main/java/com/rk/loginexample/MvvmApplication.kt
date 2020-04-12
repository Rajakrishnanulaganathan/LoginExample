package com.rk.loginexample

import android.app.Application
import com.rk.loginexample.data.remote.network.MyApi
import com.rk.loginexample.data.local.database.Appdatabase
import com.rk.loginexample.data.remote.interceptor.NetworkConnectionInterceptor
import com.rk.loginexample.data.repository.LoginRepository
import com.rk.loginexample.viewmodel.viewmodelfactory.LoginViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MvvmApplication :Application(),KodeinAware{
    override val kodein=Kodein.lazy {
        import(androidXModule(this@MvvmApplication))
        bind() from singleton{
            NetworkConnectionInterceptor(
                instance()
            )
        }
        bind() from singleton{
            MyApi(
                instance()
            )
        }
        bind() from singleton{
            Appdatabase(
                instance()
            )
        }
        bind() from singleton{
            LoginRepository(
                instance(),
                instance()
            )
        }
        bind()from provider {
            LoginViewModelFactory(
                instance()
            )
        }

    }



}