package com.example.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TvResponse
import com.example.moviecatalogue.utils.EspressoIdlingResources
import com.example.moviecatalogue.utils.api.ApiConfig
import com.example.moviecatalogue.utils.pojo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    private val api: String = BuildConfig.API_KEY

    fun getAllMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        val movieResults = MutableLiveData<ApiResponse<List<MovieResponse>>>()

        EspressoIdlingResources.increment()

        val client = ApiConfig.getApiService().getMovieTopRated(api)
        client.enqueue(object : Callback<ResponseMovieTopRated> {
            override fun onResponse(call: Call<ResponseMovieTopRated>, response: Response<ResponseMovieTopRated>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()?.results
                    if (movieResponse != null) {
                        val movieList = ArrayList<MovieResponse>()
                        for (responseItem in movieResponse) {
                            var years: String = ""
                            years = if (responseItem.releaseDate.isEmpty()) {
                                ""
                            } else {
                                responseItem.releaseDate.substring(0,4)
                            }
                            val movie = MovieResponse(
                                responseItem.id,
                                responseItem.posterPath,
                                responseItem.title,
                                years,
                                responseItem.voteAverage,
                                "-",
                                responseItem.overview,
                                responseItem.releaseDate
                            )
                            movieList.add(movie)
                        }

                        movieResults.postValue(ApiResponse.success(movieList))

                        EspressoIdlingResources.decrement()
                    }
                } else {
                    Log.d("MovieRepository", "onResponseFail: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseMovieTopRated>, t: Throwable) {
                Log.d("MovieRepository", "onFailure: {${t.message.toString()}")
            }
        })

        return movieResults
    }

    fun getAllTv(): LiveData<ApiResponse<List<TvResponse>>> {
        val tvResults = MutableLiveData<ApiResponse<List<TvResponse>>>()

        EspressoIdlingResources.increment()

        val client = ApiConfig.getApiService().getTvTopRated(api)
        client.enqueue(object : Callback<ResponseTvTopRated> {
            override fun onResponse(call: Call<ResponseTvTopRated>, response: Response<ResponseTvTopRated>) {
                if (response.isSuccessful) {
                    val tvResponses = response.body()?.results
                    if (tvResponses != null) {
                        val tvList = ArrayList<TvResponse>()
                        for (responseItem in tvResponses) {
                            var years: String = ""
                            years = if (responseItem.firstAirDate.isEmpty()) {
                                ""
                            } else {
                                responseItem.firstAirDate.substring(0,4)
                            }
                            val tv = TvResponse(
                                responseItem.id,
                                responseItem.posterPath,
                                responseItem.originalName,
                                years,
                                responseItem.voteAverage,
                                "-",
                                responseItem.overview,
                                responseItem.firstAirDate
                            )

                            tvList.add(tv)
                        }
                        tvResults.postValue(ApiResponse.success(tvList))

                        EspressoIdlingResources.decrement()
                    }
                } else {
                    Log.d("MovieRepository", "onResponseFail: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseTvTopRated>, t: Throwable) {
                Log.d("MovieRepository", "onFailure: {${t.message.toString()}")
            }
        })

        return tvResults
    }

    fun getDetailMovie(movie_id: Int): LiveData<ApiResponse<MovieResponse>> {
        val movieResult = MutableLiveData<ApiResponse<MovieResponse>>()

        EspressoIdlingResources.increment()

        val client = ApiConfig.getApiService().getDetailMovie(movie_id, api)
        client.enqueue(object : Callback<ResponseDetailMovie> {
            override fun onResponse(call: Call<ResponseDetailMovie>, response: Response<ResponseDetailMovie>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    if (movieResponse != null) {
                        var years: String = ""
                        years = if (movieResponse.releaseDate.isEmpty()) {
                            ""
                        } else {
                            movieResponse.releaseDate.substring(0,4)
                        }
                        val movie = MovieResponse(
                            movieResponse.id,
                            movieResponse.posterPath,
                            movieResponse.title,
                            years,
                            movieResponse.voteAverage,
                            checkGenre(movieResponse.genres),
                            movieResponse.overview,
                            movieResponse.releaseDate
                        )

                        movieResult.postValue(ApiResponse.success(movie))

                        EspressoIdlingResources.decrement()
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

    fun getDetailTv(tv_id: Int): LiveData<ApiResponse<TvResponse>> {
        val tvResult = MutableLiveData<ApiResponse<TvResponse>>()

        EspressoIdlingResources.increment()

        val client = ApiConfig.getApiService().getDetailTv(tv_id, api)
        client.enqueue(object : Callback<ResponseDetailTv> {
            override fun onResponse(call: Call<ResponseDetailTv>, response: Response<ResponseDetailTv>) {
                if (response.isSuccessful) {
                    val tvResponse = response.body()
                    if (tvResponse != null) {
                        var years: String = ""
                        years = if (tvResponse.lastAirDate.isEmpty()) {
                            ""
                        } else {
                            tvResponse.lastAirDate.substring(0,4)
                        }
                        val movie = TvResponse(
                            tvResponse.id,
                            tvResponse.posterPath,
                            tvResponse.originalName,
                            years,
                            tvResponse.voteAverage,
                            checkGenre(tvResponse.genres),
                            tvResponse.overview,
                            tvResponse.firstAirDate
                        )

                        tvResult.postValue(ApiResponse.success(movie))

                        EspressoIdlingResources.decrement()
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

}