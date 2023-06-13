package com.mandiriinduksi.swiftmovie.data.entities.data

import androidx.room.PrimaryKey

data class User(
    @PrimaryKey
    val id: Int,
    val username : String,
    val name: String,
    val password: String
)
