package com.mandiriinduksi.swiftmovie.data.network

import com.mandiriinduksi.swiftmovie.data.network.response.BaseMovie
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "55c3402a26c2959019ef64b285ec6b6a",
        @Query("page") page: Int
    ): Call<BaseMovie>

    @GET("movie/top_rated")
    fun getTopRatedrMovies(
        @Query("api_key") apiKey: String = "55c3402a26c2959019ef64b285ec6b6a",
        @Query("page") page: Int
    ): Call<BaseMovie>
}