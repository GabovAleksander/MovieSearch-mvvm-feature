package com.practicum.moviesearch.domain.api

import com.practicum.moviesearch.Resource
import com.practicum.moviesearch.domain.models.Movie

interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
}