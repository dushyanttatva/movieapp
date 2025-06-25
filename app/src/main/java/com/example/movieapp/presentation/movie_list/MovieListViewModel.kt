package com.example.movieapp.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.MovieResource
import com.example.movieapp.domain.usecase.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMovieList: GetMovieListUseCase
): ViewModel(){

    private val _list = MutableStateFlow(MovieListState())
    val list: StateFlow<MovieListState> = _list
    private var currentPage = 1

    fun fetchMovieList(page: Int = currentPage) {
        getMovieList(page).onEach {
            when(it) {
                is MovieResource.Loading -> {
                    _list.value = MovieListState(isLoading = true)
//                    _list.value = _list.value.copy(isLoading = true)
                }
                is MovieResource.Success -> {
                    val currentList = _list.value.data ?: emptyList()
                    val newList = currentList + (it.data ?: emptyList())
                    _list.value = MovieListState(data = newList)
//                    _list.value = _list.value.copy(
//                        isLoading = false,
//                        data = it.data,
//                        error = ""
//                    )
                }
                is MovieResource.Error -> {
                    _list.value = MovieListState(error = it.message.toString())
//                    _list.value = _list.value.copy(
//                        isLoading = false,
//                        error = it.message.orEmpty()
//                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}