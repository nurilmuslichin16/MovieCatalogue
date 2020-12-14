package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.utils.DataDummy

class DetailViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private lateinit var movieId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getMovie(): MovieEntity {
        lateinit var movie: MovieEntity
        val moviesEntities = movieRepository.getAllMovies()
        for (movieEntity in moviesEntities) {
            if (movieEntity.movieId == movieId) {
                movie = movieEntity
            }
        }
        return movie
    }

    fun getTv(): MovieEntity {
        lateinit var tv: MovieEntity
        val tvEntities = movieRepository.getAllTv()
        for (tvEntity in tvEntities) {
            if (tvEntity.movieId == movieId) {
                tv = tvEntity
            }
        }
        return tv
    }

}