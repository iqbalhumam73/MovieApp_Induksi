package com.mandiriinduksi.swiftmovie.presentation.moviedetail

import androidx.lifecycle.ViewModel
import com.mandiriinduksi.swiftmovie.data.network.ApiConstants
import com.mandiriinduksi.swiftmovie.data.network.RetrofitInstance.Companion.apiService
import com.mandiriinduksi.swiftmovie.data.network.response.Movie

class MovieDetailViewModel: ViewModel() {

    suspend fun getMovieData(movieId : Long): Movie {
        lateinit var movieReturn : Movie
        val movieResponse = apiService.getMovieDetail(movieId)

        val movieTitleResponse = movieResponse.body()?.get("title").toString().replace("\"", "")
        val movieRatingResponse = movieResponse.body()?.get("vote_average").toString().replace("\"", "")
        val movieOverviewResponse = movieResponse.body()?.get("overview").toString().replace("\"", "")
        val moviePosterResponse = movieResponse.body()?.get("poster_path").toString().replace("\"", "")


        val movieRatingResponseRound = (Math.round(movieRatingResponse.toFloat() * 10) / 10.0f).toString()

        val moviePosterUrl = ApiConstants.img_base_url+moviePosterResponse

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