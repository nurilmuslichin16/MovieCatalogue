package com.example.moviecatalogue.ui.menu.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.RMovieEntity
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.vo.Resource

class FavMovieViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getMovies(): LiveData<List<RMovieEntity>> = movieRepository.getAllFavoriteMovies()

    fun getEmptyMovie(): List<MovieEntity> = DataDummy.generateDummyEmpty()

}