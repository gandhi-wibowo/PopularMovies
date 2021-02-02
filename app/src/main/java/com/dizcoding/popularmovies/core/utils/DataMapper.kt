package com.dizcoding.popularmovies.core.utils

import com.dizcoding.popularmovies.core.data.source.local.entity.MovieEntity
import com.dizcoding.popularmovies.core.data.source.remote.network.ApiResponse
import com.dizcoding.popularmovies.core.data.source.remote.response.MovieResponse
import com.dizcoding.popularmovies.core.domain.model.Movie

object DataMapper {
    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                it.adult,
                it.backdrop_path,
                it.id,
                it.original_language,
                it.original_title,
                it.overview,
                it.popularity,
                it.poster_path,
                it.release_date,
                it.title,
                it.video,
                it.vote_average,
                it.vote_count,
                it.isFavorit
            )
        }

    fun mapEntityToDomain(movie: MovieEntity): Movie =
        Movie(
            movie.adult,
            movie.backdrop_path,
            movie.id,
            movie.original_language,
            movie.original_title,
            movie.overview,
            movie.popularity,
            movie.poster_path,
            movie.release_date,
            movie.title,
            movie.video,
            movie.vote_average,
            movie.vote_count,
            movie.isFavorit
        )

    fun mapDomainToEntities(movie: Movie): MovieEntity = MovieEntity(
        movie.adult,
        movie.backdrop_path,
        movie.id,
        movie.original_language,
        movie.original_title,
        movie.overview,
        movie.popularity,
        movie.poster_path,
        movie.release_date,
        movie.title,
        movie.video,
        movie.vote_average,
        movie.vote_count,
        movie.isFavorit
    )

    fun mapResponseToDomain(movie: MovieResponse?): List<Movie> {
        return arrayListOf<Movie>(
            Movie(
                movie?.adult,
                movie?.backdrop_path,
                movie?.id,
                movie?.original_language,
                movie?.original_title,
                movie?.overview,
                movie?.popularity,
                movie?.poster_path,
                movie?.release_date,
                movie?.title,
                movie?.video,
                movie?.vote_average,
                movie?.vote_count,
                false
            )
        )
    }

    fun mapResponseToDomain(input: List<MovieResponse>): List<Movie> =
        input.map {
            Movie(
                it.adult,
                it.backdrop_path,
                it.id,
                it.original_language,
                it.original_title,
                it.overview,
                it.popularity,
                it.poster_path,
                it.release_date,
                it.title,
                it.video,
                it.vote_average,
                it.vote_count,
                false
            )
        }

    fun mapResponseToEntity(it: MovieResponse): MovieEntity {
        return MovieEntity(
            it.adult,
            it.backdrop_path,
            it.id,
            it.original_language,
            it.original_title,
            it.overview,
            it.popularity,
            it.poster_path,
            it.release_date,
            it.title,
            it.video,
            it.vote_average,
            it.vote_count,
            false
        )
    }
}