package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.local.entity.RMovieEntity
import com.example.moviecatalogue.data.source.local.entity.RTvEntity
import com.example.moviecatalogue.vo.Resource

interface MovieDataSource {

    fun getAllMovies(): LiveData<Resource<List<RMovieEntity>>>

    fun getAllTv(): LiveData<Resource<List<RTvEntity>>>

    fun getDetailMovie(movie_id: Int): LiveData<Resource<RMovieEntity>>

    fun getDetailTv(tv_id: Int): LiveData<Resource<RTvEntity>>

    fun getAllFavoriteMovies(): LiveData<PagedList<RMovieEntity>>

    fun getAllFavoriteTv(): LiveData<PagedList<RTvEntity>>

    fun setFavoriteMovie(movie: RMovieEntity, isFavorite: Boolean)

    fun setFavoriteTv(movie: RTvEntity, isFavorite: Boolean)

}