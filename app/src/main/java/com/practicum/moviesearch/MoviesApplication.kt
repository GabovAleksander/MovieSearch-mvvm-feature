package com.practicum.moviesearch

import android.app.Application
import com.practicum.moviesearch.presentation.movies.MoviesSearchViewModel

class MoviesApplication : Application() {

    var moviesSearchViewModel : MoviesSearchViewModel? = null

}