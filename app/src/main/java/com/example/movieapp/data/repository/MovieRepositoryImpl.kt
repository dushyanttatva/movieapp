package com.example.movieapp.data.repository

import androidx.paging.PagingSource
import com.example.movieapp.data.mapper.toDomain
import com.example.movieapp.data.network.ApiService
import com.example.movieapp.data.network.SafeApiRequest
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.data.paging.MovieListPagingSource
import com.example.movieapp.domain.model.MovieCast
import com.example.movieapp.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val apiService: ApiService
): MovieRepository, SafeApiRequest() {

    override suspend fun getMovieList(page: Int): List<Movie> {
        val response = apiRequest {
            apiService.getMovieList(page)
        }
        return response.results.map {
            it.toDomain()
        }
    }

    override suspend fun getMovieCast(movieId: Int): List<MovieCast> {
        val response = apiRequest {
            apiService.getMovieCast(movieId)
        }
        return response.cast.map {
            it.toDomain()
        }
    }

    override fun getPagingSource(): PagingSource<Int, Movie> {
        return MovieListPagingSource(this)
    }
}