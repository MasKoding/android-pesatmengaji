package com.example.pesatmengaji.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
   private lateinit var  retrofit: Retrofit

  private val okHttpClient = OkHttpClient.Builder()
      .connectTimeout(60,TimeUnit.SECONDS)
      .build()

    fun getClient():Retrofit{
        retrofit = Retrofit.Builder()
            .baseUrl(API_URL.getTimingByCityURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit

    }
    fun getClientAsmaulHusna():Retrofit{
        retrofit = Retrofit.Builder()
            .baseUrl(API_URL.getAsmaulHusnaURl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit

    }




}