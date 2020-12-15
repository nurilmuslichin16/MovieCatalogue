package com.example.moviecatalogue.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.utils.DataDummy

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private var _movies = MutableLiveData<MovieEntity>()
    val movies: LiveData<MovieEntity> = _movies

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getMovies(): List<MovieEntity> {
        _movies = movieRepository.getAllMovies()
    }

    fun getEmptyMovie(): List<MovieEntity> = DataDummy.generateDummyEmpty()

}