package com.example.movieapp.common

sealed class MovieResource<T>(
    message:String? = null,
    data:T? = null){
    class Loading<T>: MovieResource<T>()
    class Success<T>(val data: T?): MovieResource<T>(data = data)
    class Error<T>(val message: String?): MovieResource<T>(message = message)
}
