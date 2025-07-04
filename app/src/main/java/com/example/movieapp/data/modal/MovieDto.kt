package com.example.movieapp.data.modal

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val id: Int,
    val title: String?,
    val overview: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("release_date")
    val releaseDate: String?
)
