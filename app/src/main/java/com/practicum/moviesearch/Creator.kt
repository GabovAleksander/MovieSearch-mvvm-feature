package com.practicum.moviesearch

import android.content.Context
import com.practicum.moviesearch.data.MoviesRepositoryImpl
import com.practicum.moviesearch.data.SharedPreferences.LocalStorage
import com.practicum.moviesearch.data.network.RetrofitNetworkClient
import com.practicum.moviesearch.domain.api.MoviesInteractor
import com.practicum.moviesearch.domain.api.MoviesRepository
import com.practicum.moviesearch.domain.impl.MoviesInteractorImpl
import com.practicum.moviesearch.presentation.movies.MoviesSearchViewModel
import com.practicum.moviesearch.presentation.poster.PosterPresenter
import com.practicum.moviesearch.ui.poster.PosterView

object Creator {

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }


   fun providePosterPresenter(
        posterView: PosterView,
        imageUrl: String
    ): PosterPresenter {
        return PosterPresenter(posterView, imageUrl)
    }

    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(
            RetrofitNetworkClient(context),
            LocalStorage(context.getSharedPreferences("local_storage", Context.MODE_PRIVATE)),
        )
    }
}