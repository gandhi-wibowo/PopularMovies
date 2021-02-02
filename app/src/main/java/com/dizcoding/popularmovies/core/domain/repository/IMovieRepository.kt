package com.dizcoding.popularmovies.core.domain.repository

import com.dizcoding.popularmovies.core.data.source.local.entity.MovieEntity
import com.dizcoding.popularmovies.core.domain.model.Movie
import com.dizcoding.popularmovies.core.vo.Resource
import io.reactivex.Flowable

interface IMovieRepository {

    fun getLocalMovie(): Flowable<List<Movie>>
    fun insertLocalMovie(movie: Movie)
    fun deleteLocalMovie(movieId: Int)

    fun getRemoteMovie(): Flowable<List<Movie>>

    fun getDetailMovie(movieId: Int): Flowable<Resource<List<Movie>>>

}