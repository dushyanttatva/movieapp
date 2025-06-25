package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.Movie

interface MovieRepository {

    suspend fun getMovieList(page: Int = 1): List<Movie>
}