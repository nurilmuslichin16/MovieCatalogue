package com.example.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.source.local.entity.RMovieEntity
import com.example.moviecatalogue.data.source.local.entity.RTvEntity
import com.example.moviecatalogue.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {

    // Movie

    fun getAllMovies(): LiveData<List<RMovieEntity>> = mMovieDao.getMovies()

    fun getDetailMovie(movieId: Int): LiveData<RMovieEntity> = mMovieDao.getMovieId(movieId)

    fun insertMovies(movies: List<RMovieEntity>) = mMovieDao.insertMovies(movies)

    fun setFavoriteMovie(movie: RMovieEntity, isFavorite: Boolean) {
        movie.isFavorite = isFavorite
        mMovieDao.updateMovie(movie)
    }

    // TV Show

    fun getAllTv(): LiveData<List<RTvEntity>> = mMovieDao.getTv()

    fun getDetailTv(movieId: Int): LiveData<RTvEntity> = mMovieDao.getTvId(movieId)

    fun insertTv(tv: List<RTvEntity>) = mMovieDao.insertTv(tv)

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