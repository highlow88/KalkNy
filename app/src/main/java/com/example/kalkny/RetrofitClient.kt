package com.example.kalkny

import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.0.93:3000/"

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient) // Set the OkHttpClient here
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getMathService(): MathService {
        return retrofit.create(MathService::class.java)
    }
}

