package com.example.movieapp.domain.repository

import androidx.paging.PagingSource
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.MovieCast

interface MovieRepository {

    suspend fun getMovieList(page: Int = 1): List<Movie>

    suspend fun getMovieCast(movieId: Int): List<MovieCast>

    fun getPagingSource(): PagingSource<Int, Movie>
}