package com.dizcoding.popularmovies.detail

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.dizcoding.core.domain.model.Movie
import com.dizcoding.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getDetail(movieId : Int)  = LiveDataReactiveStreams.fromPublisher(movieUseCase.getDetailMovie(movieId))
    fun insertFavorite(movie : Movie) = movieUseCase.insertLocalMovie(movie)
    fun deleteFavorite(movieId: Int) = movieUseCase.deleteLocalMovie(movieId)
}