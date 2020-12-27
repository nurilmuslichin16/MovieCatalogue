package com.example.moviecatalogue.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.RMovieEntity
import com.example.moviecatalogue.data.source.local.entity.RTvEntity
import com.example.moviecatalogue.data.source.remote.ApiResponse
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TvResponse
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.utils.EspressoIdlingResources
import com.example.moviecatalogue.utils.api.ApiConfig
import com.example.moviecatalogue.utils.pojo.*
import com.example.moviecatalogue.vo.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors)
    : MovieDataSource {

    override fun getAllMovies(): LiveData<Resource<List<RMovieEntity>>> {
        return object : NetworkBoundResource<List<RMovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<RMovieEntity>> =
                localDataSource.getAllMovies()

            override fun shouldFetch(data: List<RMovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            public override fun saveCallResult(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<RMovieEntity>()
                for (response in movieResponses) {
                    val movie = RMovieEntity(
                        response.movieId,
                        response.image,
                        response.title,
                        response.years,
                        response.rating,
                        response.category,
                        response.overview,
                        response.release,
                        false)

                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getAllTv(): LiveData<Resource<List<RTvEntity>>> {

    }

    override fun getDetailMovie(movie_id: Int): LiveData<Resource<RMovieEntity>> {

    }

    override fun getDetailTv(tv_id: Int): LiveData<Resource<RTvEntity>> {

    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): MovieRepository =
                instance ?: synchronized(this) {
                    instance ?: MovieRepository(remoteData, localData, appExecutors)
                }
    }

}