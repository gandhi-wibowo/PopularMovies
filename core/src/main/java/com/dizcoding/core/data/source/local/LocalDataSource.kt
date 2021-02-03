package com.dizcoding.core.data.source.local

import com.dizcoding.core.data.source.local.entity.MovieEntity
import com.dizcoding.core.data.source.local.room.MovieDao

class LocalDataSource constructor(private val movieDao: MovieDao) {

    fun getMovieFavorite() = movieDao.getMovieFavorite()
    fun getMovieFavorite(movieId : Int) = movieDao.getMovieFavorite(movieId)
    fun insertMovieFavorite(movieEntity: MovieEntity) = movieDao.insertMovieFavorite(movieEntity)
    fun deleteMovieFavorite(movieId : Int) = movieDao.deleteMovieFavorite(movieId)


}