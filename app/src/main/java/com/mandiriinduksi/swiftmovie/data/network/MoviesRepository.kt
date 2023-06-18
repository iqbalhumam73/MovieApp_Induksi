package com.mandiriinduksi.swiftmovie.data.network

import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRepository {

    private val apiService: ApiService
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getPopularMovies(page: Int) = apiService.getPopularMovies(page = page)

    fun getTopRatedMovies(page: Int) = apiService.getTopRatedrMovies(page = page)

    suspend fun getMovieDetail(movie_id : Long) = apiService.getMovieDetail(movie_id)

    fun exploreMovie(page : Int, searchInput: String) = apiService.exploreMovie(page = page, searchInput = searchInput)

}