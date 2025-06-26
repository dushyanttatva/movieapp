package com.example.movieapp.common

sealed class MovieDetailsResource<T>(
    message:String? = null,
    data:T? = null
) {
    class Loading<T>: MovieDetailsResource<T>()
    class Success<T>(val data: T?): MovieDetailsResource<T>(data = data)
    class Error<T>(val message: String?): MovieDetailsResource<T>(message = message)
}