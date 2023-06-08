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

    fun getPopularMovies(page: Int = 1, onSuccess: (movies: List<Movie>) -> Unit, onError: () -> Unit){
        Log.d("Repository", "Running getPopularMovies function")
        apiService.getPopularMovies(page = page).enqueue(object: Callback<BaseMovie>{
            override fun onResponse(call: Call<BaseMovie>, response: Response<BaseMovie>){
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
//                        Log.d("Repository", "Moviesaaa: ${responseBody}")
                        onSuccess.invoke(responseBody.movies)
                    }
                    else{
//                        Log.d("Repository", "Response null :D")
                        onError.invoke()
                    }
                }
            }

            override fun onFailure(call: Call<BaseMovie>, t: Throwable) {
//                Log.d("Respository", "Failed ")
                onError.invoke()
            }
        })
    }

    fun getTopRatedMovies(page: Int = 1, onSuccess: (movies: List<Movie>) -> Unit, onError: () -> Unit){
        apiService.getTopRatedrMovies(page = page).enqueue(object: Callback<BaseMovie>{
            override fun onResponse(call: Call<BaseMovie>, response: Response<BaseMovie>) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        onSuccess.invoke(responseBody.movies)
                    }
                    else {
                        onError.invoke()
                    }
                }
            }
            override fun onFailure(call: Call<BaseMovie>, t: Throwable) {
                onError.invoke()
            }
        })
    }
}