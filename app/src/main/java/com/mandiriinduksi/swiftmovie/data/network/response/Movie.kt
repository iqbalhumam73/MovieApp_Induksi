package com.mandiriinduksi.swiftmovie.data.network.response

import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("genre_ids")
    val genreIds: IntArray?,

    @SerializedName("id")
    val id: Long,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("vote_average")
    val rating: Float?
)
