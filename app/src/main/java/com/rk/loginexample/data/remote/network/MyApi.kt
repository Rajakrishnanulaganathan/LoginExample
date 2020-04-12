package com.rk.loginexample.data.remote.network

import com.rk.loginexample.data.remote.interceptor.NetworkConnectionInterceptor
import com.rk.loginexample.data.remote.model.AuthResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(@Field("email") email: String, @Field("password") password: String): Response<AuthResponse>

    @FormUrlEncoded
    @POST("signup")
    suspend fun signup(@Field("name") name:String ,@Field("email") email: String, @Field("password") password: String): Response<AuthResponse>


    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): MyApi {
            val looginInterceptor = HttpLoggingInterceptor()
            looginInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor).addInterceptor(looginInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}