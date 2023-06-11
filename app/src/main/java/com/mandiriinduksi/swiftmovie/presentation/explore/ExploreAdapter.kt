package com.mandiriinduksi.swiftmovie.presentation.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandiriinduksi.swiftmovie.data.network.response.Movie
import com.mandiriinduksi.swiftmovie.databinding.ExploreMovieCardBinding
import com.mandiriinduksi.swiftmovie.databinding.HomeMovieCardBinding
import com.mandiriinduksi.swiftmovie.presentation.home.MovieAdapter
import com.mandiriinduksi.swiftmovie.presentation.home.MovieViewHolder
import com.mandiriinduksi.swiftmovie.presentation.home.OnAdapterListener

class ExploreAdapter(
    private var movies: List<Movie>,
    private val listener: OnAdapterListener): RecyclerView.Adapter<ExploreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        return ExploreViewHolder(ExploreMovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        val movie = movies[position]

        val moviePosterUrl = "https://image.tmdb.org/t/p/w342${movie.posterPath}"

        holder.movieTitle.text = movie.title
        Glide.with(holder.moviePoster)
            .load(moviePosterUrl)
            .into(holder.moviePoster)

        holder.card.setOnClickListener(){
            listener.onCLick(movie)
        }
    }

    fun updateMovie (movies: List<Movie>){
        this.movies = movies
        notifyDataSetChanged()
    }
}