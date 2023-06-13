package com.mandiriinduksi.swiftmovie.presentation.home

import android.util.JsonReader
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.mandiriinduksi.swiftmovie.data.network.MoviesRepository
import com.mandiriinduksi.swiftmovie.data.network.RetrofitInstance
import com.mandiriinduksi.swiftmovie.data.network.response.BaseMovie
import com.mandiriinduksi.swiftmovie.data.network.response.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class HomeViewModel: ViewModel() {

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

    suspend fun getMainMovie() : Movie {
        lateinit var movieReturn : Movie
        var randomId : Long = 0


        lateinit var movieResponse : Response<JsonObject>
        var moviePosterResponse : String? = null
        var movieTitleResponse : String? = null
        var movieRatingResponse : Float? = null
        var movieOverviewResponse : String? = null
        var movieAdultResponse : String? = null

        var count = 1
        do {
            randomId = Random.nextLong(600000)
            Log.d("moviemaintitle", "run : ${count}")
            movieResponse = RetrofitInstance.apiService.getMovieDetail(randomId)
            moviePosterResponse = movieResponse.body()?.get("poster_path").toString().replace("\"", "")
            movieAdultResponse = movieResponse.body()?.get("adult").toString().replace("\"", "")
            Log.d("moviemaintitle", "poster path @ VM : ${moviePosterResponse}")
            Log.d("moviemaintitle", "poster adult @ VM : ${movieAdultResponse}")
            count++
        }while (moviePosterResponse == "null" || movieAdultResponse == "true")

         movieTitleResponse = movieResponse.body()?.get("title").toString().replace("\"", "")
         movieOverviewResponse = movieResponse.body()?.get("overview").toString().replace("\"", "")


        val moviePosterUrl = "https://image.tmdb.org/t/p/w342${moviePosterResponse}"
        Log.d("movieposterresponse", moviePosterUrl)

        movieReturn = Movie(
            adult = null,
            id = randomId,
            title = movieTitleResponse,
            rating = null,
            overview = movieOverviewResponse,
            posterPath = moviePosterUrl,
            backdropPath = null,
            genreIds = null
        )

        return movieReturn
    }
}