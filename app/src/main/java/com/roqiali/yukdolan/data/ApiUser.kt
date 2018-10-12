package com.roqiali.yukdolan.data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiUser {
    companion object {
        fun getClient() : Retrofit {
            val BASE_URL = "https://erporate.com/"
            val retrofit: Retrofit = Retrofit.Builder().
                    baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit
        }
    }
}