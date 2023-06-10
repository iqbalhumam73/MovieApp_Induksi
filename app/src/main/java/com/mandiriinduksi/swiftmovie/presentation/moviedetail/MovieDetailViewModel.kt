package com.mandiriinduksi.swiftmovie.presentation.moviedetail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mandiriinduksi.swiftmovie.data.network.MoviesRepository
import retrofit2.Response
import com.mandiriinduksi.swiftmovie.data.network.response.BaseMovie
import javax.security.auth.callback.Callback

class MovieDetailViewModel: ViewModel() {

    fun getMovieDetail(movie_id: Long, page: Int){
        val response = MoviesRepository.getMovieDetail(movie_id, page)
        Log.d("response", response.toString())
    }
}