package com.mandiriinduksi.swiftmovie.presentation.explore

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mandiriinduksi.swiftmovie.data.network.MoviesRepository
import com.mandiriinduksi.swiftmovie.data.network.response.BaseMovie
import com.mandiriinduksi.swiftmovie.data.network.response.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreViewModel: ViewModel(){

    fun exploreMovie(page: Int = 1, searchInput: String, onSuccess: (movies: List<Movie>) -> Unit, onError: () -> Unit){
        MoviesRepository.exploreMovie(page, searchInput).enqueue(object:
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
                onError.invoke()
            }
        })
    }
}