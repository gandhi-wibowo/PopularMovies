package com.dizcoding.popularmovies

import android.app.Application
import com.dizcoding.core.di.databaseModule
import com.dizcoding.core.di.networkModule
import com.dizcoding.core.di.repositoryModule
import com.dizcoding.popularmovies.di.useCaseModule
import com.dizcoding.popularmovies.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(listOf(
                databaseModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            ))
        }
    }
}