package com.dizcoding.core.data.source.local.room

import androidx.room.*
import com.dizcoding.core.data.source.local.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Query("SELECT * FROM tb_movie_favorite")
    fun getMovieFavorite() : Flowable<List<MovieEntity>>

    @Query("SELECT * FROM tb_movie_favorite WHERE id = :movieId")
    fun getMovieFavorite(movieId : Int) : Flowable<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieFavorite(movie: MovieEntity) : Completable

    @Query("DELETE FROM tb_movie_favorite WHERE id = :movieId")
    fun deleteMovieFavorite(movieId : Int)
}