package com.example.pesatmengaji.service

import com.example.pesatmengaji.data.AsmulHusna
import com.example.pesatmengaji.data.Data
import com.example.pesatmengaji.data.PrayerTime
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("timingsByCity?city=Jakarta&country=Indonesia&method=8")
    fun getTimingByCity():Call<PrayerTime>

    @GET("api/get/asmaul_husna")
    fun getAsmaulHusnaList():Call<AsmulHusna>

    @GET("api/search/asmaul_husna/{search}")
    fun getAsmaulHusnaSearch(@Path("search") search:String):Call<AsmulHusna>
}