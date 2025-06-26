package com.example.movieapp.domain.usecase

import com.example.movieapp.common.MovieDetailsResource
import com.example.movieapp.domain.model.MovieCast
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetMovieCastUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<MovieDetailsResource<List<MovieCast>>> = flow {
        emit(MovieDetailsResource.Loading())
        try {
            emit(MovieDetailsResource.Success(data = repository.getMovieCast(movieId)))
        } catch (e: Exception) {
            emit(MovieDetailsResource.Error(message = e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}