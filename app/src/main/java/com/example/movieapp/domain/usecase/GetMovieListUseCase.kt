package com.example.movieapp.domain.usecase

import com.example.movieapp.common.MovieResource
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetMovieListUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke(page: Int = 1): Flow<MovieResource<List<Movie>>> = flow {
        emit(MovieResource.Loading())
        try {
            emit(MovieResource.Success(data = repository.getMovieList(page)))
        }catch (e: Exception) {
            emit(MovieResource.Error(message = e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}