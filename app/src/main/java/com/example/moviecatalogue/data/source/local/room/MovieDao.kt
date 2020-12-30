package com.example.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.moviecatalogue.data.source.local.entity.RMovieEntity
import com.example.moviecatalogue.data.source.local.entity.RTvEntity

@Dao
interface MovieDao {

    // Movie

    @Query("SELECT * FROM movieentities")
    fun getMovies(): DataSource.Factory<Int, RMovieEntity>

    @Query("SELECT * FROM movieentities WHERE isFavorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, RMovieEntity>

    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    fun getMovieId(movieId: Int): LiveData<RMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<RMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movies: RMovieEntity)

    @Update
    fun updateMovie(movie: RMovieEntity)

    // TV Show

    @Query("SELECT * FROM tventities")
    fun getTv(): DataSource.Factory<Int, RTvEntity>

    @Query("SELECT * FROM tventities WHERE isFavorite = 1")
    fun getFavoriteTv(): DataSource.Factory<Int, RTvEntity>

    @Query("SELECT * FROM tventities WHERE movieId = :movieId")
    fun getTvId(movieId: Int): LiveData<RTvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tv: List<RTvEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvDetail(tv: RTvEntity)

    @Update
    fun updateTv(tv: RTvEntity)

}