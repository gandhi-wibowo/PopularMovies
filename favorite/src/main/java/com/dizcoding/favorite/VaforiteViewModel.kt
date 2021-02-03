package com.dizcoding.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.dizcoding.core.domain.usecase.MovieUseCase

class VaforiteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getLocalMovie() = LiveDataReactiveStreams.fromPublisher(movieUseCase.getLocalMovie())
}