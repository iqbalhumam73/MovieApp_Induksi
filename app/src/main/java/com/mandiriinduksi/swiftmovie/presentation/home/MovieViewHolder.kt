package com.mandiriinduksi.swiftmovie.presentation.home

import androidx.recyclerview.widget.RecyclerView
import com.mandiriinduksi.swiftmovie.databinding.HomeMovieCardBinding

class MovieViewHolder(val recyclerBinding: HomeMovieCardBinding): RecyclerView.ViewHolder(recyclerBinding.root) {
    var moviePoster = recyclerBinding.ivMoviePoster
    var movieTitle = recyclerBinding.tvMovieTitle
}