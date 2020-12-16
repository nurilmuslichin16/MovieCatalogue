package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TvResponse
import com.example.moviecatalogue.utils.DataDummy

class DetailViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private lateinit var id: String

    fun setSelectedMovie(movieId: Int) {
        this.id = movieId.toString()
    }

    fun getMovie(): LiveData<MovieResponse> = movieRepository.getDetailMovie(id.toInt())

    fun getTv(): LiveData<TvResponse> = movieRepository.getDetailTv(id.toInt())

}