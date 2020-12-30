package com.example.moviecatalogue.ui.menu.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.RMovieEntity
import com.example.moviecatalogue.utils.DataDummy

class FavMovieViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getMovies(): LiveData<PagedList<RMovieEntity>> = movieRepository.getAllFavoriteMovies()

    fun setFavorite(movie: RMovieEntity) {
        val isFavorite = !movie.isFavorite
        movieRepository.setFavoriteMovie(movie, isFavorite)
    }

    fun getEmptyMovie(): List<MovieEntity> = DataDummy.generateDummyEmpty()

}