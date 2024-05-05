package com.practicum.moviesearch.data

import com.practicum.moviesearch.Resource
import com.practicum.moviesearch.data.SharedPreferences.LocalStorage
import com.practicum.moviesearch.data.dto.MoviesSearchRequest
import com.practicum.moviesearch.data.dto.MoviesSearchResponse
import com.practicum.moviesearch.domain.api.MoviesRepository
import com.practicum.moviesearch.domain.models.Movie

class MoviesRepositoryImpl(private val networkClient: NetworkClient, private val localStorage: LocalStorage) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                val stored = localStorage.getSavedFavorites()
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description, inFavorite = stored.contains(it.id))})
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }

    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(movie.id)
    }
}