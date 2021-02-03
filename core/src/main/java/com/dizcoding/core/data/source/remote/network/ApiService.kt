package com.dizcoding.core.data.source.remote.network

import com.dizcoding.core.BuildConfig
import com.dizcoding.core.data.source.remote.response.MovieResponse
import com.dizcoding.core.data.source.remote.response.MovieResult
import io.reactivex.Flowable
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