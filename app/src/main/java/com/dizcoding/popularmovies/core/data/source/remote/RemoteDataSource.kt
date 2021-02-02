package com.dizcoding.popularmovies.core.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dizcoding.popularmovies.core.data.source.remote.network.ApiService
import com.dizcoding.popularmovies.core.data.source.remote.response.MovieResponse
import com.dizcoding.popularmovies.core.data.source.remote.response.MovieResult
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource constructor(private val apiService: ApiService) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getMovies(): Flowable<List<MovieResponse>> {
        val resultData = PublishSubject.create<List<MovieResponse>>()
        val client = apiService.getMovie()
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.results
                resultData.onNext(dataArray)
            }, { error ->
                resultData.onNext(listOf())
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getMovieDetail(movieId: Int): Flowable<MovieResponse> {
        val resultData = PublishSubject.create<MovieResponse>()
        val client = apiService.getMovieDetail(movieId)
        val nullResponse = MovieResponse(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                resultData.onNext(response)
            }, { error ->
                resultData.onNext(nullResponse)
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

}