package com.dizcoding.popularmovies.core.di

import android.content.Context
import com.dizcoding.popularmovies.core.data.MovieRepository
import com.dizcoding.popularmovies.core.data.source.local.LocalDataSource
import com.dizcoding.popularmovies.core.data.source.local.room.MovieDatabase
import com.dizcoding.popularmovies.core.data.source.remote.RemoteDataSource
import com.dizcoding.popularmovies.core.data.source.remote.network.ApiConfig
import com.dizcoding.popularmovies.core.domain.repository.IMovieRepository
import com.dizcoding.popularmovies.core.domain.usecase.MovieInteractor
import com.dizcoding.popularmovies.core.domain.usecase.MovieUseCase
import com.dizcoding.popularmovies.core.utils.AppExecutors

object Injection {

    private fun provideRepository(context: Context): IMovieRepository {
        val database = MovieDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()
        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMovieUseCase(context: Context): MovieUseCase {
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }

}