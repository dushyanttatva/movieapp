package com.example.movieapp.data.network

import com.example.movieapp.data.modal.MovieCastResponseDto
import com.example.movieapp.data.modal.MovieListResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovieList(
        @Query("page") page: Int
    ): Response<MovieListResponseDto>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Path("movie_id") movieId: Int
    ): Response<MovieCastResponseDto>

}