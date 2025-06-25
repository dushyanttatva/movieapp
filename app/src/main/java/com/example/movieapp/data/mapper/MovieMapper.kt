package com.example.movieapp.data.mapper

import com.example.movieapp.data.modal.MovieDto
import com.example.movieapp.domain.model.Movie

fun MovieDto.toDomain(): Movie = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    releaseDate = releaseDate,
)