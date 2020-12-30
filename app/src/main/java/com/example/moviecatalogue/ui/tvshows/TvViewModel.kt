package com.example.moviecatalogue.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.RTvEntity
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.vo.Resource

class TvViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getTv(): LiveData<Resource<PagedList<RTvEntity>>> = movieRepository.getAllTv()

    fun getEmptyTv(): List<MovieEntity> = DataDummy.generateDummyEmpty()
}