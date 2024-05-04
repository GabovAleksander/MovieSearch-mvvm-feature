package com.practicum.moviesearch.presentation.poster

import com.practicum.moviesearch.ui.poster.PosterView

class PosterPresenter(
    private val view: PosterView,
    private val imageUrl: String,
) {

    fun onCreate() {
        view.setupPosterImage(imageUrl)
    }
}