package com.example.moviecatalogue.ui.tvshows

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.utils.DataDummy

class TvViewModel: ViewModel() {

    fun getTv(): List<MovieEntity> = DataDummy.generateDummyTv()

}