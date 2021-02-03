package com.dizcoding.popularmovies.movie

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.dizcoding.core.domain.usecase.MovieUseCase

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getMovie() = LiveDataReactiveStreams.fromPublisher(movieUseCase.getRemoteMovie())
}