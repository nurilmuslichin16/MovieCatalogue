package com.example.moviecatalogue.data.source.remote

import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    fun getAllMovies(): List<MovieEntity> = jsonHelper.loadMovies()

    fun getAllTv(): List<MovieEntity> = jsonHelper.loadTv()

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(helper)
                }
    }

}