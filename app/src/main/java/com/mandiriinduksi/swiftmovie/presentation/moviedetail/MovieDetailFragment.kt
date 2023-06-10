package com.mandiriinduksi.swiftmovie.presentation.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mandiriinduksi.swiftmovie.R
import com.mandiriinduksi.swiftmovie.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {

    lateinit var binding : FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    var moviePoster : String = ""
    var movieTitle : String = ""
    var movieOverview : String = ""
    var movieRating : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        moviePoster = args.moviePoster
        movieTitle = args.movieTitle
        movieOverview = args.movieOverview
        movieRating = args.movieRating

        binding.apply {
            tvMovieTitle.setText(movieTitle)
            tvMovieOverview.setText(movieOverview)
            tvMovieRating.setText(movieRating)

            val moviePosterUrl = "https://image.tmdb.org/t/p/w342${moviePoster}"

            Glide.with(requireActivity())
                .load(moviePosterUrl)
                .into(ivMoviePoster)

        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MovieDetailFragment()
    }
}