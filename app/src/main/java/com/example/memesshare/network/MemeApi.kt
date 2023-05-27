package com.example.memesshare.network

import com.example.memesshare.model.Meme
import retrofit2.Call
import retrofit2.http.GET

interface MemeApi {

    @GET("gimme")
    fun getMeme(): Call<Meme>
}