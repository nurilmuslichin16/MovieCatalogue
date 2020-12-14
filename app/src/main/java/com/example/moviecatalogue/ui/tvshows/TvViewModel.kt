package com.example.moviecatalogue.ui.tvshows

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.utils.DataDummy

class TvViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getTv(): List<MovieEntity> = movieRepository.getAllTv()

    fun getEmptyTv(): List<MovieEntity> = DataDummy.generateDummyEmpty()
}