package com.example.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.RMovieEntity
import com.example.moviecatalogue.data.source.local.entity.RTvEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TvResponse
import com.example.moviecatalogue.ui.FakeMovieRepository
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.LiveDataTestUtil
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)

    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val movieResponse = DataDummy.generateDummyResponseMovie()
    private val movieDetailResponse = DataDummy.generateDummyResponseMovie()[0]
    private val movieId = movieResponse[0].movieId

    private val tvResponse = DataDummy.generateDummyResponseTv()
    private val tvDetailResponse = DataDummy.generateDummyResponseTv()[0]
    private val tvId = movieResponse[0].movieId

    @Test
    fun testGetAllMovies() {
        val liveMovie = MutableLiveData<List<RMovieEntity>>()
        liveMovie.value = movieResponse

        `when`(local.getAllMovies()).thenReturn(liveMovie)
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getAllMovies())
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun testGetAllTv() {
        val liveTv = MutableLiveData<List<RTvEntity>>()
        liveTv.value = tvResponse

        `when`(local.getAllTv()).thenReturn(liveTv)
        val tvEntities = LiveDataTestUtil.getValue(movieRepository.getAllTv())
        verify(local).getAllTv()
        assertNotNull(tvEntities.data)
        assertEquals(movieResponse.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun testGetDetailMovie() {
        val liveDetailMovie = MutableLiveData<RMovieEntity>()
        liveDetailMovie.value = movieDetailResponse

        `when`(local.getDetailMovie(movieId)).thenReturn(liveDetailMovie)
        val movieDetailEntities = LiveDataTestUtil.getValue(movieRepository.getDetailMovie(movieId))
        verify(local).getDetailMovie(movieId)

        assertNotNull(movieDetailEntities)
        assertEquals(movieDetailResponse.movieId, movieDetailEntities.data?.movieId)
        assertEquals(movieDetailResponse.category, movieDetailEntities.data?.category)
        assertEquals(movieDetailResponse.image, movieDetailEntities.data?.image)
        assertEquals(movieDetailResponse.overview, movieDetailEntities.data?.overview)
        assertEquals(movieDetailResponse.rating, movieDetailEntities.data?.rating)
        assertEquals(movieDetailResponse.release, movieDetailEntities.data?.release)
        assertEquals(movieDetailResponse.title, movieDetailEntities.data?.title)
        assertEquals(movieDetailResponse.years, movieDetailEntities.data?.years)
    }

    @Test
    fun testGetDetailTv() {
        val liveDetailTv = MutableLiveData<RTvEntity>()
        liveDetailTv.value = tvDetailResponse

        `when`(local.getDetailTv(tvId)).thenReturn(liveDetailTv)
        val tvDetailEntities = LiveDataTestUtil.getValue(movieRepository.getDetailTv(tvId))
        verify(local).getDetailTv(tvId)

        assertNotNull(tvDetailEntities)
        assertEquals(tvDetailResponse.movieId, tvDetailEntities.data?.movieId)
        assertEquals(tvDetailResponse.category, tvDetailEntities.data?.category)
        assertEquals(tvDetailResponse.image, tvDetailEntities.data?.image)
        assertEquals(tvDetailResponse.overview, tvDetailEntities.data?.overview)
        assertEquals(tvDetailResponse.rating, tvDetailEntities.data?.rating)
        assertEquals(tvDetailResponse.release, tvDetailEntities.data?.release)
        assertEquals(tvDetailResponse.title, tvDetailEntities.data?.title)
        assertEquals(tvDetailResponse.years, tvDetailEntities.data?.years)
    }
}