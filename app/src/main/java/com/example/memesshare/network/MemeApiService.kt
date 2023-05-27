package com.example.memesshare.network

import com.example.memesshare.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MemeApiService {

    fun getMemeApi(): MemeApi {
        val api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Utils.BASEURL)
            .build()
            .create(MemeApi::class.java)
        return api
    }
}