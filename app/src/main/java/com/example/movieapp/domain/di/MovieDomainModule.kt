package com.example.movieapp.domain.di

import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.usecase.GetMovieCastUseCase
import com.example.movieapp.domain.usecase.GetMovieListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object MovieDomainModule {

    @Provides
    fun provideGetMovieListUseCase(repository: MovieRepository): GetMovieListUseCase {
        return GetMovieListUseCase(repository = repository)
    }

    @Provides
    fun provideGetMovieCastUseCase(repository: MovieRepository): GetMovieCastUseCase {
        return GetMovieCastUseCase(repository = repository)
    }
}