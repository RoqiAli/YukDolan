package com.roqiali.yukdolan.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("bootcamp/jsonBootcamp.php")
    fun getPlace(): Call<PlaceModel>
}