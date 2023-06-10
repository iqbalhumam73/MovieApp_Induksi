package com.mandiriinduksi.swiftmovie.presentation.home

import com.mandiriinduksi.swiftmovie.data.network.response.Movie

interface OnAdapterListener {
    fun onCLick(movie: Movie)
}