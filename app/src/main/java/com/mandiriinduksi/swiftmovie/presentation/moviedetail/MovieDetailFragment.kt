package com.mandiriinduksi.swiftmovie.presentation.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mandiriinduksi.swiftmovie.data.network.ApiService
import com.mandiriinduksi.swiftmovie.data.network.RetrofitInstance
import com.mandiriinduksi.swiftmovie.data.network.response.Movie
import com.mandiriinduksi.swiftmovie.databinding.FragmentMovieDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MovieDetailFragment : Fragment() {

    lateinit var binding : FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    lateinit var movieDetailViewModel : MovieDetailViewModel

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apiService = RetrofitInstance.apiService

        movieDetailViewModel = ViewModelProvider(requireActivity())[MovieDetailViewModel::class.java]

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
        val movieIdParam = args.movieIdArgs
        CoroutineScope(Dispatchers.Main).launch {
            setMovieDetailData(movieIdParam)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MovieDetailFragment()
    }

    suspend fun setMovieDetailData(id : Long){
        val movie : Movie = movieDetailViewModel.getMovieData(id)
        binding.apply {
            tvMovieTitle.setText(movie.title)
            tvMovieRating.setText(movie.rating.toString())
            tvMovieOverview.setText(movie.overview)

            Glide.with(requireActivity())
                .load(movie.posterPath)
                .into(binding.ivMoviePoster)
        }
    }

}