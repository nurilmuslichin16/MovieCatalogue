package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.source.remote.ApiResponse
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TvResponse

interface MovieDataSource {

    fun getAllMovies(): LiveData<ApiResponse<List<MovieResponse>>>

    fun getAllTv(): LiveData<ApiResponse<List<TvResponse>>>

    fun getDetailMovie(movie_id: Int): LiveData<ApiResponse<MovieResponse>>

    fun getDetailTv(tv_id: Int): LiveData<ApiResponse<TvResponse>>

}