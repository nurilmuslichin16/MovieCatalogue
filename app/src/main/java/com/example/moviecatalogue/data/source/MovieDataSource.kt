package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TvResponse

interface MovieDataSource {

    fun getAllMovies(): LiveData<List<MovieResponse>>

    fun getAllTv(): LiveData<List<TvResponse>>

    fun getDetailMovie(movie_id: Int): LiveData<MovieResponse>

    fun getDetailTv(tv_id: Int): LiveData<TvResponse>

}