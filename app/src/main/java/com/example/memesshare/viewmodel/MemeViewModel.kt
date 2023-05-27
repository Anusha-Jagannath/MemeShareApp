package com.example.memesshare.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memesshare.model.Meme
import com.example.memesshare.network.MemeApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MemeViewModel : ViewModel() {

    private val _memeData = MutableLiveData<Meme>()
    val memeData = _memeData as LiveData<Meme>

    private val _loader = MutableLiveData<Boolean>()
    val loader = _loader as LiveData<Boolean>

    private val _error = MutableLiveData<String>()
    val error = _error as LiveData<String>


    fun getMeme() {
        val retrofit = MemeApiService().getMemeApi().getMeme()
        _loader.value = true
        retrofit.enqueue(object : Callback<Meme> {
            override fun onResponse(call: Call<Meme>, response: Response<Meme>) {
                _memeData.value = response.body()
                _loader.value = false
            }

            override fun onFailure(call: Call<Meme>, t: Throwable) {
                _loader.value = false
                _error.value = t.message
            }

        })
    }

}