package com.dizcoding.popularmovies.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dizcoding.popularmovies.core.di.Injection
import com.dizcoding.popularmovies.core.domain.usecase.MovieUseCase
import com.dizcoding.popularmovies.detail.DetailViewModel
import com.dizcoding.popularmovies.movie.MovieViewModel
import com.dizcoding.popularmovies.vaforite.VaforiteViewModel

class ViewModelFactory private constructor(private val movieUseCase: MovieUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideMovieUseCase(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(VaforiteViewModel::class.java) -> {
                VaforiteViewModel(movieUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}