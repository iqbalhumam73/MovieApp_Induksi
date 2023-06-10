package com.mandiriinduksi.swiftmovie.presentation.moviedetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mandiriinduksi.swiftmovie.R
import com.mandiriinduksi.swiftmovie.data.network.ApiService
import com.mandiriinduksi.swiftmovie.data.network.RetrofitInstance
import com.mandiriinduksi.swiftmovie.data.network.response.Movie
import com.mandiriinduksi.swiftmovie.databinding.FragmentMovieDetailBinding
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.math.log


class MovieDetailFragment : Fragment() {

    lateinit var binding : FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    var moviePosterParam : String = ""
    var movieTitleParam : String = ""
    var movieOverviewParam : String = ""
    var movieRatingParam : String = ""
    var movieIdParam : Long = 0

    //test
    private lateinit var apiService: ApiService
    //test

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //test
        apiService = RetrofitInstance.apiService
        //test
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviePosterParam = args.moviePosterArgs
        movieTitleParam = args.movieTitleArgs
        movieOverviewParam = args.movieOverviewArgs
        movieRatingParam = args.movieRatingArgs

        binding.apply {
            tvMovieTitle.setText(movieTitleParam)
            tvMovieOverview.setText(movieOverviewParam)
            tvMovieRating.setText(movieRatingParam)

            val moviePosterUrl = "https://image.tmdb.org/t/p/w342${moviePosterParam}"

            Glide.with(requireActivity())
                .load(moviePosterUrl)
                .into(ivMoviePoster)
        }

        //test
        getMovieData(movieIdParam)
        //test
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MovieDetailFragment()
    }

    fun getMovieData(movieId : Long){
        CoroutineScope(Dispatchers.IO).launch {
            val movie = apiService.getMovieDetail(movieId)
//            val responseName = movie.body()?.title.toString()
//            Log.d("movieResp", responseName)
        }
    }
}