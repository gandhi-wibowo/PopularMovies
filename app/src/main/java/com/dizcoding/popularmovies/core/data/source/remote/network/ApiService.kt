package com.dizcoding.popularmovies.core.data.source.remote.network

import com.dizcoding.popularmovies.BuildConfig
import com.dizcoding.popularmovies.core.data.source.remote.response.*
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("popular")
    fun getMovie(
        @Query("api_key") api_key: String = BuildConfig.IMDB_TOKEN
    ): Flowable<MovieResult>

    @GET("{movieId}")
    fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") api_key: String = BuildConfig.IMDB_TOKEN
    ): Flowable<MovieResponse>

}