package com.example.movieapp.data.network

import com.example.movieapp.data.modal.MovieListResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovieList(
        @Query("page") page: Int
    ): Response<MovieListResponseDto>

}