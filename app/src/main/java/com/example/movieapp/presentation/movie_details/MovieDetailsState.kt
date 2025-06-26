package com.example.movieapp.presentation.movie_details

import com.example.movieapp.domain.model.MovieCast

data class MovieCastState(
    val isLoading:Boolean = false,
    val error:String = "",
    val data:List<MovieCast>? = null
)
