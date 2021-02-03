package com.dizcoding.popularmovies.di

import com.dizcoding.core.domain.usecase.MovieInteractor
import com.dizcoding.core.domain.usecase.MovieUseCase
import com.dizcoding.popularmovies.detail.DetailViewModel
import com.dizcoding.popularmovies.movie.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}