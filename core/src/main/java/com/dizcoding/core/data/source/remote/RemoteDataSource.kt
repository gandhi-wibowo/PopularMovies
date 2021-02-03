package com.dizcoding.core.data.source.remote

import com.dizcoding.core.data.source.remote.network.ApiService
import com.dizcoding.core.data.source.remote.response.MovieResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource constructor(private val apiService: ApiService) {

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
            }, { _ ->
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
            }, { _ ->
                resultData.onNext(nullResponse)
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

}