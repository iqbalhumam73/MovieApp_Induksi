package com.mandiriinduksi.swiftmovie.data.network

import com.google.gson.JsonObject
import com.mandiriinduksi.swiftmovie.data.network.response.BaseMovie
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = ApiConstants.api_key,
        @Query("page") page: Int
    ): Call<BaseMovie>

    @GET("movie/top_rated")
    fun getTopRatedrMovies(
        @Query("api_key") apiKey: String = ApiConstants.api_key,
        @Query("page") page: Int
    ): Call<BaseMovie>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") apiKey: String = ApiConstants.api_key
    ): Response<JsonObject>

    @GET("search/movie")
    fun exploreMovie(
        @Query("api_key") apiKey: String = ApiConstants.api_key,
        @Query("page") page: Int,
        @Query("query") searchInput: String
    ): Call<BaseMovie>
}