package com.mandiriinduksi.swiftmovie.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitInstance {
    companion object{
        val base_url = "https://api.themoviedb.org/3/"

        fun getRetrofit(): Retrofit {

            return Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val apiService: ApiService by lazy {
            getRetrofit().create(ApiService::class.java)
        }
    }
}