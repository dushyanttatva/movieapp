package com.example.movieapp.data.mapper

import com.example.movieapp.data.modal.MovieCastDto
import com.example.movieapp.domain.model.MovieCast

fun MovieCastDto.toDomain(): MovieCast = MovieCast(
    id = id,
    name = name,
    character = character,
    profilePath = profilePath,
)