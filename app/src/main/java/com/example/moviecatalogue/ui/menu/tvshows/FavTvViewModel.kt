package com.example.moviecatalogue.ui.menu.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.RTvEntity
import com.example.moviecatalogue.utils.DataDummy

class FavTvViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getTv(): LiveData<PagedList<RTvEntity>> = movieRepository.getAllFavoriteTv()

    fun setFavorite(tv: RTvEntity) {
        val isFavorite = !tv.isFavorite
        movieRepository.setFavoriteTv(tv, isFavorite)
    }

    fun getEmptyTv(): List<MovieEntity> = DataDummy.generateDummyEmpty()
}