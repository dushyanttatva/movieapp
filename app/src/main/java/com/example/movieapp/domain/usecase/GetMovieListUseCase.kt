package com.example.movieapp.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieListUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { repository.getPagingSource() }
        ).flow
    }
}