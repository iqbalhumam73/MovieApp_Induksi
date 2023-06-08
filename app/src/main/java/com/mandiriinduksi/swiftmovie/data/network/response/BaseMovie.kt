package com.mandiriinduksi.swiftmovie.data.network.response

import com.google.gson.annotations.SerializedName

data class BaseMovie(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val movies: List<Movie>,

    @SerializedName("total_pages")
    val pages: Int
)
