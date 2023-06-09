package com.mandiriinduksi.swiftmovie.presentation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mandiriinduksi.swiftmovie.data.network.MoviesRepository
import com.mandiriinduksi.swiftmovie.data.network.response.BaseMovie
import com.mandiriinduksi.swiftmovie.data.network.response.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    private val MoviePopularList = MutableLiveData<List<Movie>>()
    private val ErrorMessage = MutableLiveData<String>()

    fun getPopularMovies(page: Int = 1, onSuccess: (movies: List<Movie>) -> Unit, onError: () -> Unit){
        Log.d("Repository", "Running getPopularMovies function")
        MoviesRepository.getPopularMovies(page).enqueue(object:
            Callback<BaseMovie> {
            override fun onResponse(call: Call<BaseMovie>, response: Response<BaseMovie>){
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        onSuccess.invoke(responseBody.movies)
                    }
                    else{
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
        MoviesRepository.getTopRatedMovies(page).enqueue(object: Callback<BaseMovie>{
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