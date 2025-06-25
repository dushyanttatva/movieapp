package com.example.movieapp.data.repository

import com.example.movieapp.data.mapper.toDomain
import com.example.movieapp.data.network.ApiService
import com.example.movieapp.data.network.SafeApiRequest
import com.example.movieapp.domain.model.Movie
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
}