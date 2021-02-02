package com.dizcoding.popularmovies.core.data

import com.dizcoding.popularmovies.core.data.source.local.LocalDataSource
import com.dizcoding.popularmovies.core.data.source.remote.RemoteDataSource
import com.dizcoding.popularmovies.core.domain.model.Movie
import com.dizcoding.popularmovies.core.domain.repository.IMovieRepository
import com.dizcoding.popularmovies.core.utils.AppExecutors
import com.dizcoding.popularmovies.core.utils.DataMapper
import com.dizcoding.popularmovies.core.vo.Resource
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {


    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getLocalMovie(): Flowable<List<Movie>> {
        return localDataSource.getMovieFavorite().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun insertLocalMovie(movie: Movie) {
        val movieEntity = DataMapper.mapDomainToEntities(movie)
        localDataSource.insertMovieFavorite(movieEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun deleteLocalMovie(movieId: Int) {
        appExecutors.diskIO().execute { localDataSource.deleteMovieFavorite(movieId) }
    }

    override fun getRemoteMovie(): Flowable<List<Movie>> {
        return remoteDataSource.getMovies().map { DataMapper.mapResponseToDomain(it) }
    }

    override fun getDetailMovie(movieId: Int): Flowable<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>>() {
            override fun loadFromDB(): Flowable<List<Movie>> {
                return localDataSource.getMovieFavorite(movieId).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data?.isEmpty()!!

            override fun createCall(): Flowable<List<Movie>> {
                return remoteDataSource.getMovieDetail(movieId)
                    .map { DataMapper.mapResponseToDomain(it) }
            }

        }.asFlowable()
    }


}