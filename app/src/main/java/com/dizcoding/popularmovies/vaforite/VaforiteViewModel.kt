package com.dizcoding.popularmovies.vaforite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.dizcoding.popularmovies.core.domain.usecase.MovieUseCase

class VaforiteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getLocalMovie() = LiveDataReactiveStreams.fromPublisher(movieUseCase.getLocalMovie())

}