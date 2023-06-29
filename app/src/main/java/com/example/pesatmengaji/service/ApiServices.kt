package com.example.pesatmengaji.service

import com.example.pesatmengaji.data.Data
import com.example.pesatmengaji.data.PrayerTime
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("timingsByCity?city=Jakarta&country=Indonesia&method=8")
    fun getTimingByCity():Call<PrayerTime>
}