package com.mandiriinduksi.swiftmovie.presentation.explore

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mandiriinduksi.swiftmovie.databinding.ExploreMovieCardBinding

class ExploreViewHolder(val recyclerBinding: ExploreMovieCardBinding): ViewHolder(recyclerBinding.root) {
    var moviePoster = recyclerBinding.ivMoviePoster
    var movieTitle = recyclerBinding.tvMovieTitle
    var card = recyclerBinding.cardExploreMovie
}