package com.mandiriinduksi.swiftmovie.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.mandiriinduksi.swiftmovie.data.network.ApiService
import com.mandiriinduksi.swiftmovie.data.network.RetrofitInstance
import com.mandiriinduksi.swiftmovie.data.network.response.Movie
import com.mandiriinduksi.swiftmovie.databinding.FragmentHomeBinding
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var apiService: ApiService


    lateinit var moviePopularAdapter: MoviePopularAdapter
    lateinit var movieTopRatedAdapter: MovieTopRatedAdapter

    lateinit var moviePopularRecyclerView: RecyclerView
    lateinit var movieTopRatedRecyclerView: RecyclerView

    private val homeViewModel: HomeViewModel by viewModels()
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

        CoroutineScope(Dispatchers.Main).launch {
            getMainMovie()
            setupPopularRV()
            setupTopRatedRV()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment()
    }

    suspend fun getMainMovie(){
        val movieMain = homeViewModel.getMainMovie()
        binding.apply {
            Glide.with(requireActivity())
                .load(movieMain.posterPath)
                .into(binding.ivMainMovie)
        }
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

    private fun setupPopularMovieAdapter(){
        moviePopularAdapter = MoviePopularAdapter(listOf(), object : OnAdapterListener{
            override fun onCLick(movie: Movie) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(
                    movie.id
                ))
            }
        })
        moviePopularRecyclerView.adapter = moviePopularAdapter
    }

    private fun setupTopRatedMovieAdapter(){
        movieTopRatedAdapter = MovieTopRatedAdapter(listOf(), object : OnAdapterListener{
            override fun onCLick(movie: Movie) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(
                    movie.id
                ))
            }
        })
        movieTopRatedRecyclerView.adapter = movieTopRatedAdapter
    }

    private fun onPopularMoviesFetched(movies: List<Movie>){
        moviePopularAdapter.updateMovie(movies)
    }

    private fun onTopRatedMoviesFetched(movies: List<Movie>){
        movieTopRatedAdapter.updateMovie(movies)
    }

    private fun onError(){
    }
}