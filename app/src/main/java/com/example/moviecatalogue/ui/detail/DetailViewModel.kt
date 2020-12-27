package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.RMovieEntity
import com.example.moviecatalogue.data.source.local.entity.RTvEntity
import com.example.moviecatalogue.vo.Resource

class DetailViewModel(private val movieRepository: MovieRepository): ViewModel() {

    val movieId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    var movieDetail: LiveData<Resource<RMovieEntity>> = Transformations.switchMap(movieId) { id ->
        movieRepository.getDetailMovie(id)
    }

    var tvDetail: LiveData<Resource<RTvEntity>> = Transformations.switchMap(movieId) { id ->
        movieRepository.getDetailTv(id)
    }

    fun setFavoriteMovie() {
        val movieResource = movieDetail.value
        if (movieResource != null) {
            val movie = movieResource.data
            if (movie != null) {
                val isFavorite = !movie.isFavorite
                movieRepository.setFavoriteMovie(movie, isFavorite)
            }
        }
    }

    fun setFavoriteTv() {
        val tvResource = tvDetail.value
        if (tvResource != null) {
            val tv = tvResource.data
            if (tv != null) {
                val isFavorite = !tv.isFavorite
                movieRepository.setFavoriteTv(tv, isFavorite)
            }
        }
    }
}