package com.anime.app.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val API_URL = "https://animechan.vercel.app/"

    private val retrofit by lazy{
        Retrofit.Builder().baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: AnimeChanService by lazy {
        retrofit.create(AnimeChanService::class.java)
    }
}