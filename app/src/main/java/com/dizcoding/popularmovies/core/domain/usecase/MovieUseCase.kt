package com.dizcoding.popularmovies.core.domain.usecase

import com.dizcoding.popularmovies.core.domain.model.Movie
import com.dizcoding.popularmovies.core.vo.Resource
import io.reactivex.Flowable

interface MovieUseCase {
    fun getLocalMovie(): Flowable<List<Movie>>
    fun insertLocalMovie(movie: Movie)
    fun deleteLocalMovie(movieId: Int)

    fun getRemoteMovie(): Flowable<List<Movie>>

    fun getDetailMovie(movieId: Int): Flowable<Resource<List<Movie>>>
}