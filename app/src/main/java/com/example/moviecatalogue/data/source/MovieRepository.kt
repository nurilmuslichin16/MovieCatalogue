package com.example.moviecatalogue.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TvResponse
import com.example.moviecatalogue.utils.api.ApiConfig
import com.example.moviecatalogue.utils.pojo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource): MovieDataSource {

    val api: String = "b179796c705d7efea1a2bea1c4217a3f"

    override fun getAllMovies(): LiveData<List<MovieResponse>> {
        val movieResults = MutableLiveData<List<MovieResponse>>()

        val client = ApiConfig.getApiService().getMovieTopRated(api)
        client.enqueue(object : Callback<ResponseMovieTopRated> {
            override fun onResponse(call: Call<ResponseMovieTopRated>, response: Response<ResponseMovieTopRated>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()?.results
                    if (movieResponse != null) {
                        val movieList = ArrayList<MovieResponse>()
                        for (responseItem in movieResponse) {
                            val movie = MovieResponse(
                                    responseItem.id,
                                    responseItem.posterPath,
                                    responseItem.title,
                                    responseItem.releaseDate.substring(0,4),
                                    responseItem.voteAverage,
                                    "-",
                                    responseItem.overview,
                                    responseItem.releaseDate
                            )
                            movieList.add(movie)
                        }

                        movieResults.postValue(movieList)
                    }
                } else {
                    Log.d("MovieRepository", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseMovieTopRated>, t: Throwable) {
                Log.d("MovieRepository", "onFailure: {${t.message.toString()}")
            }
        })

        return movieResults
    }

    override fun getAllTv(): LiveData<List<TvResponse>> {
        val tvResults = MutableLiveData<List<TvResponse>>()

        val client = ApiConfig.getApiService().getTvTopRated(api)
        client.enqueue(object : Callback<ResponseTvTopRated> {
            override fun onResponse(call: Call<ResponseTvTopRated>, response: Response<ResponseTvTopRated>) {
                if (response.isSuccessful) {
                    val tvResponses = response.body()?.results
                    if (tvResponses != null) {
                        val tvList = ArrayList<TvResponse>()
                        for (responseItem in tvResponses) {
                            val tv = TvResponse(
                                    responseItem.id,
                                    responseItem.posterPath,
                                    responseItem.originalName,
                                    responseItem.firstAirDate.substring(0,4),
                                    responseItem.voteAverage,
                                    "-",
                                    responseItem.overview,
                                    responseItem.firstAirDate
                            )

                            tvList.add(tv)
                        }
                        tvResults.postValue(tvList)
                    }
                } else {
                    Log.d("MovieRepository", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseTvTopRated>, t: Throwable) {
                Log.d("MovieRepository", "onFailure: {${t.message.toString()}")
            }
        })

        return tvResults
    }

    override fun getDetailMovie(movie_id: Int): LiveData<MovieResponse> {
        val movieResult = MutableLiveData<MovieResponse>()

        val client = ApiConfig.getApiService().getDetailMovie(movie_id, api)
        client.enqueue(object : Callback<ResponseDetailMovie> {
            override fun onResponse(call: Call<ResponseDetailMovie>, response: Response<ResponseDetailMovie>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    if (movieResponse != null) {
                        val movie = MovieResponse(
                                movieResponse.id,
                                movieResponse.posterPath,
                                movieResponse.title,
                                movieResponse.releaseDate.substring(0,4),
                                movieResponse.voteAverage,
                                checkGenre(movieResponse.genres),
                                movieResponse.overview,
                                movieResponse.releaseDate
                        )

                        movieResult.postValue(movie)
                    }
                }
            }

            private fun checkGenre(genre: List<GenresItem>): String {
                var genreResult: String = ""
                when {
                    genre.size == 1 -> {
                        for (item in genre) {
                            genreResult = item.name
                        }
                    }
                    genre.isNullOrEmpty() -> {
                        genreResult = "-"
                    }
                    else -> {
                        var no: Int = 0
                        for (item in genre) {
                            if (no == 0) {
                                genreResult += item.name
                                no++
                            } else {
                                genreResult += ", " + item.name
                                no++
                            }
                        }
                    }
                }

                return genreResult
            }

            override fun onFailure(call: Call<ResponseDetailMovie>, t: Throwable) {
                Log.d("MovieRepository", "onFailure: {${t.message.toString()}")
            }
        })

        return movieResult
    }

    override fun getDetailTv(tv_id: Int): LiveData<TvResponse> {
        val tvResult = MutableLiveData<TvResponse>()

        val client = ApiConfig.getApiService().getDetailTv(tv_id, api)
        client.enqueue(object : Callback<ResponseDetailTv> {
            override fun onResponse(call: Call<ResponseDetailTv>, response: Response<ResponseDetailTv>) {
                if (response.isSuccessful) {
                    val tvResponse = response.body()
                    if (tvResponse != null) {
                        val movie = TvResponse(
                                tvResponse.id,
                                tvResponse.posterPath,
                                tvResponse.originalName,
                                tvResponse.lastAirDate.substring(0,4),
                                tvResponse.voteAverage,
                                checkGenre(tvResponse.genres),
                                tvResponse.overview,
                                tvResponse.lastAirDate
                        )

                        tvResult.postValue(movie)
                    }
                }
            }

            private fun checkGenre(genre: List<TvGenresItem>): String {
                var genreResult: String = ""
                when {
                    genre.size == 1 -> {
                        for (item in genre) {
                            genreResult = item.name
                        }
                    }
                    genre.isNullOrEmpty() -> {
                        genreResult = "-"
                    }
                    else -> {
                        var no: Int = 0
                        for (item in genre) {
                            if (no == 0) {
                                genreResult += item.name
                                no++
                            } else {
                                genreResult += ", " + item.name
                                no++
                            }
                        }
                    }
                }

                return genreResult
            }

            override fun onFailure(call: Call<ResponseDetailTv>, t: Throwable) {
                Log.d("MovieRepository", "onFailure: {${t.message.toString()}")
            }
        })

        return tvResult
    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteData: RemoteDataSource): MovieRepository =
                instance ?: synchronized(this) {
                    instance ?: MovieRepository(remoteData)
                }
    }

}