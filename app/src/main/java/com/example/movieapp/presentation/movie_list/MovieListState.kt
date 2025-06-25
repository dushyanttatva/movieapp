package com.example.movieapp.presentation.movie_list

import com.example.movieapp.domain.model.Movie

data class MovieListState(
    val isLoading:Boolean = false,
    val error: String = "",
    val data: List<Movie>? = null
)
