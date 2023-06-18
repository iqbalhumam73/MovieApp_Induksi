package com.mandiriinduksi.swiftmovie.presentation.explore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandiriinduksi.swiftmovie.data.network.ApiService
import com.mandiriinduksi.swiftmovie.data.network.RetrofitInstance
import com.mandiriinduksi.swiftmovie.data.network.response.Movie
import com.mandiriinduksi.swiftmovie.databinding.FragmentExploreBinding
import com.mandiriinduksi.swiftmovie.presentation.home.OnAdapterListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExploreFragment : Fragment() {

    private lateinit var binding : FragmentExploreBinding
    private lateinit var apiService : ApiService

    lateinit var movieExploreRecyclerView: RecyclerView

    lateinit var movieExploreAdapter: ExploreAdapter

    lateinit var exploreViewModel : ExploreViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exploreViewModel = ViewModelProvider(requireActivity())[ExploreViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupApi()

        binding.svSearchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                setupExploreRV(newText)
                return true
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExploreFragment()
    }

    fun setupApi(){
        apiService = RetrofitInstance.apiService
    }

    fun setupLayoutManager(): RecyclerView.LayoutManager {
        val verticalCardLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        return verticalCardLayoutManager
    }

    fun setupExploreRV(searchInput: String){
        movieExploreRecyclerView = binding.rvSearchResult
        val layoutManager = setupLayoutManager()
        movieExploreRecyclerView.layoutManager = layoutManager
        setupExploreMovieAdapter()

        exploreViewModel.exploreMovie(onSuccess = :: onExploreMoviesFetched, onError = :: onError, searchInput = searchInput)
    }

    fun setupExploreMovieAdapter(){
        movieExploreAdapter = ExploreAdapter(listOf(), object : OnAdapterListener{
            override fun onCLick(movie: Movie) {
                findNavController().navigate(
                    ExploreFragmentDirections.actionExploreFragmentToMovieDetailFragment(
                    movie.id
                ))
            }

        })
        movieExploreRecyclerView.adapter = movieExploreAdapter
    }

    private fun onExploreMoviesFetched(movies: List<Movie>){
        movieExploreAdapter.updateMovie(movies)
    }

    private fun onError(){
        Log.d("Repository", "Response null from main activity")
    }



}