package com.example.moviecatalogue.ui

import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.source.MovieDataSource
import com.example.moviecatalogue.data.source.remote.RemoteDataSource

class FakeMovieRepository (private val remoteDataSource: RemoteDataSource): MovieDataSource {

    override fun getAllMovies(): List<MovieEntity> {
        val movieResponses = remoteDataSource.getAllMovies()
        val movieList = ArrayList<MovieEntity>()
        for (response in movieResponses) {
            val movie = MovieEntity(
                    response.movieId,
                    response.image,
                    response.title,
                    response.years,
                    response.rating,
                    response.category,
                    response.overview,
                    response.release
            )

            movieList.add(movie)
        }
        return movieList
    }

    override fun getAllTv(): List<MovieEntity> {
        val tvResponses = remoteDataSource.getAllTv()
        val tvList = ArrayList<MovieEntity>()
        for (response in tvResponses) {
            val tv = MovieEntity(
                    response.movieId,
                    response.image,
                    response.title,
                    response.years,
                    response.rating,
                    response.category,
                    response.overview,
                    response.release
            )

            tvList.add(tv)
        }
        return tvList
    }
}