package com.example.moviecatalogue.data.source

import com.example.moviecatalogue.data.MovieEntity

interface MovieDataSource {

    fun getAllMovies(): List<MovieEntity>

    fun getAllTv(): List<MovieEntity>

}