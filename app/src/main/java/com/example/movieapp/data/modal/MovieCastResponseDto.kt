package com.example.movieapp.data.modal

import com.google.gson.annotations.SerializedName

data class MovieCastResponseDto(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("cast") var cast: List<MovieCastDto>,
)
