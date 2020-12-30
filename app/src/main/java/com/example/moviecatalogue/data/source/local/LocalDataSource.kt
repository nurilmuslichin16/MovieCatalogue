package com.example.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.moviecatalogue.data.source.local.entity.RMovieEntity
import com.example.moviecatalogue.data.source.local.entity.RTvEntity
import com.example.moviecatalogue.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {

    // Movie

    fun getAllMovies(): DataSource.Factory<Int, RMovieEntity> = mMovieDao.getMovies()

    fun getDetailMovie(movieId: Int): LiveData<RMovieEntity> = mMovieDao.getMovieId(movieId)

    fun insertMovies(movies: List<RMovieEntity>) = mMovieDao.insertMovies(movies)

    fun insertMovieDetail(movies: RMovieEntity) = mMovieDao.insertMovieDetail(movies)

    fun getAllFavoriteMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, RMovieEntity> = mMovieDao.getFavoriteMovies(query)

    fun setFavoriteMovie(movie: RMovieEntity, isFavorite: Boolean) {
        movie.isFavorite = isFavorite
        mMovieDao.updateMovie(movie)
    }

    // TV Show

    fun getAllTv(): DataSource.Factory<Int, RTvEntity> = mMovieDao.getTv()

    fun getDetailTv(movieId: Int): LiveData<RTvEntity> = mMovieDao.getTvId(movieId)

    fun insertTv(tv: List<RTvEntity>) = mMovieDao.insertTv(tv)

    fun insertTvDetail(tv: RTvEntity) = mMovieDao.insertTvDetail(tv)

    fun getAllFavoriteTv(query: SupportSQLiteQuery): DataSource.Factory<Int, RTvEntity> = mMovieDao.getFavoriteTv(query)

    fun setFavoriteTv(tv: RTvEntity, isFavorite: Boolean) {
        tv.isFavorite = isFavorite
        mMovieDao.updateTv(tv)
    }

    companion object {
        private var INSTANCE : LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

}