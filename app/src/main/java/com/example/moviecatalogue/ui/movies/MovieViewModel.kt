package com.example.moviecatalogue.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.utils.DataDummy

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getMovies(): LiveData<List<MovieResponse>> = movieRepository.getAllMovies()

    fun getEmptyMovie(): List<MovieEntity> = DataDummy.generateDummyEmpty()

}