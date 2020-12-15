package com.example.moviecatalogue.utils.api

import com.example.moviecatalogue.utils.pojo.ResponseDetailMovie
import com.example.moviecatalogue.utils.pojo.ResponseDetailTv
import com.example.moviecatalogue.utils.pojo.ResponseMovieTopRated
import com.example.moviecatalogue.utils.pojo.ResponseTvTopRated
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/top_rated")
    fun getMovieTopRated(): Call<ResponseMovieTopRated>

    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") movie_id: String): Call<ResponseDetailMovie>

    @GET("tv/top_rated")
    fun getTvTopRated(): Call<ResponseTvTopRated>

    @GET("tv/{tv_id}")
    fun getDetailTv(@Path("tv_id") tv_id: String): Call<ResponseDetailTv>
}