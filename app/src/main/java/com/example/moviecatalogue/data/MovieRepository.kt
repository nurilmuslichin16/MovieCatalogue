package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.RMovieEntity
import com.example.moviecatalogue.data.source.local.entity.RTvEntity
import com.example.moviecatalogue.data.source.remote.ApiResponse
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TvResponse
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.vo.Resource

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

            public override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<RMovieEntity>()
                for (response in data) {
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
        return object : NetworkBoundResource<List<RTvEntity>, List<TvResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<RTvEntity>> =
                    localDataSource.getAllTv()

            override fun shouldFetch(data: List<RTvEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvResponse>>> =
                    remoteDataSource.getAllTv()

            public override fun saveCallResult(data: List<TvResponse>) {
                val tvList = ArrayList<RTvEntity>()
                for (response in data) {
                    val tv = RTvEntity(
                            response.movieId,
                            response.image,
                            response.title,
                            response.years,
                            response.rating,
                            response.category,
                            response.overview,
                            response.release,
                            false)

                    tvList.add(tv)
                }
                localDataSource.insertTv(tvList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movie_id: Int): LiveData<Resource<RMovieEntity>> {
        return object : NetworkBoundResource<RMovieEntity, MovieResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<RMovieEntity> =
                    localDataSource.getDetailMovie(movie_id)

            override fun shouldFetch(data: RMovieEntity?): Boolean =
                    data == null

            public override fun createCall(): LiveData<ApiResponse<MovieResponse>> =
                    remoteDataSource.getDetailMovie(movie_id)

            public override fun saveCallResult(data: MovieResponse) {
                val movie = RMovieEntity(
                        data.movieId,
                        data.image,
                        data.title,
                        data.years,
                        data.rating,
                        data.category,
                        data.overview,
                        data.release,
                        false
                )
                localDataSource.insertMovieDetail(movie)
            }
        }.asLiveData()
    }

    override fun getDetailTv(tv_id: Int): LiveData<Resource<RTvEntity>> {
        return object : NetworkBoundResource<RTvEntity, TvResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<RTvEntity> =
                    localDataSource.getDetailTv(tv_id)

            override fun shouldFetch(data: RTvEntity?): Boolean =
                    data == null

            public override fun createCall(): LiveData<ApiResponse<TvResponse>> =
                    remoteDataSource.getDetailTv(tv_id)

            public override fun saveCallResult(data: TvResponse) {
                val tv = RTvEntity(
                        data.movieId,
                        data.image,
                        data.title,
                        data.years,
                        data.rating,
                        data.category,
                        data.overview,
                        data.release,
                        false
                )
                localDataSource.insertTvDetail(tv)
            }
        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: RMovieEntity, isFavorite: Boolean) =
            appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, isFavorite) }

    override fun setFavoriteTv(movie: RTvEntity, isFavorite: Boolean) =
            appExecutors.diskIO().execute { localDataSource.setFavoriteTv(movie, isFavorite) }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): MovieRepository =
                instance ?: synchronized(this) {
                    instance ?: MovieRepository(remoteData, localData, appExecutors)
                }
    }

}