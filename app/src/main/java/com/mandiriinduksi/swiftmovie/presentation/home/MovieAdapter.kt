package com.mandiriinduksi.swiftmovie.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandiriinduksi.swiftmovie.data.network.response.Movie
import com.mandiriinduksi.swiftmovie.databinding.HomeMovieCardBinding

open class MovieAdapter (private var movies: List<Movie>): RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(HomeMovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        val moviePosterUrl = "https://image.tmdb.org/t/p/w342${movie.posterPath}"

        holder.movieTitle.text = movie.title
        Glide.with(holder.moviePoster)
            .load(moviePosterUrl)
            .into(holder.moviePoster)
    }

//    fun addData(list: List<Movie>){
//        movies.addAll(list)
//        notifyDataSetChanged()
//    }

    fun updateMovie (movies: List<Movie>){
        this.movies = movies
        notifyDataSetChanged()
    }
}