package com.example.movieapp.presentation.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.MovieDetailsResource
import com.example.movieapp.domain.usecase.GetMovieCastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieCastUseCase: GetMovieCastUseCase
): ViewModel() {
    private val _movieCast = MutableStateFlow(MovieCastState())
    val movieCast: StateFlow<MovieCastState> = _movieCast

    fun fetchMovieCast(movieId: Int) {
        getMovieCastUseCase(movieId).onEach {
            when(it) {
                is MovieDetailsResource.Loading -> {
                    _movieCast.value = MovieCastState(isLoading = true)
                }
                is MovieDetailsResource.Success -> {
                    _movieCast.value = MovieCastState(data = it.data)
                }
                is MovieDetailsResource.Error -> {
                    _movieCast.value = MovieCastState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}