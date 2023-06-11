package com.mandiriinduksi.swiftmovie.presentation.moviedetail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import com.mandiriinduksi.swiftmovie.data.network.MoviesRepository
import com.mandiriinduksi.swiftmovie.data.network.RetrofitInstance.Companion.apiService
import retrofit2.Response
import com.mandiriinduksi.swiftmovie.data.network.response.BaseMovie
import com.mandiriinduksi.swiftmovie.data.network.response.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.security.auth.callback.Callback
import kotlin.random.Random

class MovieDetailViewModel: ViewModel() {

    fun getMovieDetail(movie_id: Long, page: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val response = MoviesRepository.getMovieDetail(movie_id, page)
            Log.d("response", response.toString())
        }
    }

    suspend fun getMovieData(movieId : Long): Movie {
        lateinit var movieReturn : Movie
        val movieResponse = apiService.getMovieDetail(movieId)

        val movieTitleResponse = movieResponse.body()?.get("title").toString().replace("\"", "")
        val movieRatingResponse = movieResponse.body()?.get("vote_average").toString().replace("\"", "")
        val movieOverviewResponse = movieResponse.body()?.get("overview").toString().replace("\"", "")
        val moviePosterResponse = movieResponse.body()?.get("poster_path").toString().replace("\"", "")


        val movieRatingResponseRound = (Math.round(movieRatingResponse.toFloat() * 10) / 10.0f).toString()

        val moviePosterUrl = "https://image.tmdb.org/t/p/w342${moviePosterResponse}"
        Log.d("movieposterresponse", moviePosterUrl)

        movieReturn = Movie(
            adult = null,
            id = movieId,
            title = movieTitleResponse,
            rating = movieRatingResponseRound.toFloat(),
            overview = movieOverviewResponse,
            posterPath = moviePosterUrl,
            backdropPath = null,
            genreIds = null
        )

        return movieReturn
    }
}