package com.mandiriinduksi.swiftmovie.presentation.home

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.mandiriinduksi.swiftmovie.R
import com.mandiriinduksi.swiftmovie.data.network.ApiService
import com.mandiriinduksi.swiftmovie.data.network.MoviesRepository
import com.mandiriinduksi.swiftmovie.data.network.RetrofitInstance
import com.mandiriinduksi.swiftmovie.data.network.response.Movie
import com.mandiriinduksi.swiftmovie.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var apiService: ApiService

//    lateinit var movieAdapter: MovieAdapter
    lateinit var moviePopularAdapter: MoviePopularAdapter
    lateinit var movieTopRatedAdapter: MovieTopRatedAdapter

    lateinit var moviePopularRecyclerView: RecyclerView
    lateinit var movieTopRatedRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupApi()
        setupLayoutManager()
        setupTopRatedRV()
        setupPopularRV()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment()
    }

    private fun setupApi(){
        apiService = RetrofitInstance.apiService
    }

    private fun setupLayoutManager() : LayoutManager{
        val horizontalCardLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        return horizontalCardLayoutManager
    }

    private fun setupPopularRV(){
        moviePopularRecyclerView = binding.rvPopularMovies
        val layoutManager = setupLayoutManager()
        moviePopularRecyclerView.layoutManager = layoutManager
        setupPopularMovieAdapter()

        MoviesRepository.getPopularMovies(onSuccess = ::onPopularMoviesFetched, onError = ::onError)
    }

    private fun setupTopRatedRV(){
        movieTopRatedRecyclerView = binding.rvTopRatedMovies
        val layoutManager = setupLayoutManager()
        movieTopRatedRecyclerView.layoutManager = layoutManager
        setupTopRatedMovieAdapter()

        MoviesRepository.getTopRatedMovies(onSuccess = ::onTopRatedMoviesFetched, onError = ::onError)
    }

    //-----------------------------------------------------------------------
    private fun setupPopularMovieAdapter(){
        moviePopularAdapter = MoviePopularAdapter(listOf())
        moviePopularRecyclerView.adapter = moviePopularAdapter
    }

    private fun setupTopRatedMovieAdapter(){
        movieTopRatedAdapter = MovieTopRatedAdapter(listOf())
        movieTopRatedRecyclerView.adapter = movieTopRatedAdapter
    }


    // ---------------------------------------------------------------------
    private fun onPopularMoviesFetched(movies: List<Movie>){
        Log.d("Repository", "Moviesaaa: $movies from main activity")
        moviePopularAdapter.updateMovie(movies)
    }

    private fun onTopRatedMoviesFetched(movies: List<Movie>){
        Log.d("Repository", "Moviesaaa: $movies from main activity")
        movieTopRatedAdapter.updateMovie(movies)
    }

    private fun onError(){
        Log.d("Repository", "Response null from main activity")
    }
}