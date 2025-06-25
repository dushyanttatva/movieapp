package com.example.movieapp.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.usecase.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    getMovieList: GetMovieListUseCase
): ViewModel(){

    val movies: Flow<PagingData<Movie>> = getMovieList().cachedIn(viewModelScope)
}