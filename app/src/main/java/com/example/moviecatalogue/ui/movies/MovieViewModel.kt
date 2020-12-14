package com.example.moviecatalogue.ui.movies

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.utils.DataDummy

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getMovies(): List<MovieEntity> = movieRepository.getAllMovies()

    fun getEmptyMovie(): List<MovieEntity> = DataDummy.generateDummyEmpty()

}