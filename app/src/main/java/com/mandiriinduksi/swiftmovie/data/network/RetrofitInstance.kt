package com.mandiriinduksi.swiftmovie.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitInstance {
    companion object{
        val base_url = "https://api.themoviedb.org/3/"

        fun getRetrofit(): Retrofit {
//            val loggingInterceptor = if(BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//            else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
//            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

            return Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
                .build()
        }

        val apiService: ApiService by lazy {
            getRetrofit().create(ApiService::class.java)
        }
    }
}