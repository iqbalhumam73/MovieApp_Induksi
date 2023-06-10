package com.mandiriinduksi.swiftmovie.data.network

import android.util.Log
import com.mandiriinduksi.swiftmovie.data.network.response.BaseMovie
import com.mandiriinduksi.swiftmovie.data.network.response.Movie
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRepository {

    private val apiService: ApiService
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getPopularMovies(page: Int) = apiService.getPopularMovies(page = page)

    fun getTopRatedMovies(page: Int) = apiService.getTopRatedrMovies(page = page)

    fun getMovieDetail(movie_id : Long, page : Int) = apiService.getMovieDetail(movie_id)

}