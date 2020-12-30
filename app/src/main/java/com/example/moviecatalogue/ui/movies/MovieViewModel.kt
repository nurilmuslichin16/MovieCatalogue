package com.example.moviecatalogue.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.RMovieEntity
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.vo.Resource

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<RMovieEntity>>> = movieRepository.getAllMovies()

    fun getEmptyMovie(): List<MovieEntity> = DataDummy.generateDummyEmpty()

}