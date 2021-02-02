package com.dizcoding.popularmovies.core.domain.usecase

import com.dizcoding.popularmovies.core.domain.model.Movie
import com.dizcoding.popularmovies.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getLocalMovie() = movieRepository.getLocalMovie()
    override fun insertLocalMovie(movie: Movie) = movieRepository.insertLocalMovie(movie)
    override fun deleteLocalMovie(movieId: Int) = movieRepository.deleteLocalMovie(movieId)
    override fun getRemoteMovie()= movieRepository.getRemoteMovie()
    override fun getDetailMovie(movieId: Int) = movieRepository.getDetailMovie(movieId)

}