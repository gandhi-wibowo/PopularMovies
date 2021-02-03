package com.dizcoding.core.data


import com.dizcoding.core.data.source.local.LocalDataSource
import com.dizcoding.core.data.source.remote.RemoteDataSource
import com.dizcoding.core.domain.model.Movie
import com.dizcoding.core.domain.repository.IMovieRepository
import com.dizcoding.core.utils.AppExecutors
import com.dizcoding.core.utils.DataMapper
import com.dizcoding.core.vo.Resource
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

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