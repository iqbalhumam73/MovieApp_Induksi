package com.mandiriinduksi.swiftmovie.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.mandiriinduksi.swiftmovie.data.network.ApiService
import com.mandiriinduksi.swiftmovie.data.network.RetrofitInstance
import com.mandiriinduksi.swiftmovie.data.network.response.Movie
import com.mandiriinduksi.swiftmovie.databinding.FragmentHomeBinding
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var apiService: ApiService


    lateinit var moviePopularAdapter: MoviePopularAdapter
    lateinit var movieTopRatedAdapter: MovieTopRatedAdapter

    lateinit var moviePopularRecyclerView: RecyclerView
    lateinit var movieTopRatedRecyclerView: RecyclerView

    lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
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
        setupPopularRV()
        setupTopRatedRV()
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

        homeViewModel.getPopularMovies(onSuccess = ::onPopularMoviesFetched, onError = ::onError)
    }

    private fun setupTopRatedRV(){
        movieTopRatedRecyclerView = binding.rvTopRatedMovies
        val layoutManager = setupLayoutManager()
        movieTopRatedRecyclerView.layoutManager = layoutManager
        setupTopRatedMovieAdapter()

        homeViewModel.getTopRatedMovies(onSuccess = ::onTopRatedMoviesFetched, onError = ::onError)
    }

    //-----------------------------------------------------------------------
    private fun setupPopularMovieAdapter(){
        moviePopularAdapter = MoviePopularAdapter(listOf(), object : OnAdapterListener{
            override fun onCLick(movie: Movie) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie.title, movie.overview, movie.rating.toString(), movie.posterPath))
            }
        })
        moviePopularRecyclerView.adapter = moviePopularAdapter
    }

    private fun setupTopRatedMovieAdapter(){
        movieTopRatedAdapter = MovieTopRatedAdapter(listOf(), object : OnAdapterListener{
            override fun onCLick(movie: Movie) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(
                    movie.title,
                    movie.overview,
                    movie.rating.toString(),
                    movie.posterPath
                ))
            }
        })
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