package com.example.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TvResponse
import com.example.moviecatalogue.ui.FakeMovieRepository
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.LiveDataTestUtil
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private val movieRepository = FakeMovieRepository()

    private val movieResponse = DataDummy.generateDummyResponseMovie()
    private val movieDetailResponse = DataDummy.generateDummyResponseMovie()[0]
    private val movieId = movieResponse[0].movieId

    private val tvResponse = DataDummy.generateDummyResponseTv()
    private val tvDetailResponse = DataDummy.generateDummyResponseTv()[0]
    private val tvId = movieResponse[0].movieId

    @Test
    fun testGetAllMovies() {
        val liveMovie = MutableLiveData<List<MovieResponse>>()
        liveMovie.value = movieResponse

        `when`(movieRepository.getAllMovies()).thenReturn(liveMovie)
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getAllMovies())
        verify(movieRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun testGetAllTv() {
        val liveTv = MutableLiveData<List<TvResponse>>()
        liveTv.value = tvResponse

        `when`(movieRepository.getAllTv()).thenReturn(liveTv)
        val tvEntities = LiveDataTestUtil.getValue(movieRepository.getAllTv())
        verify(movieRepository).getAllTv()
        assertNotNull(tvEntities)
        assertEquals(movieResponse.size.toLong(), tvEntities.size.toLong())
    }

    @Test
    fun testGetDetailMovie() {
        val liveDetailMovie = MutableLiveData<MovieResponse>()
        liveDetailMovie.value = movieDetailResponse

        `when`(movieRepository.getDetailMovie(movieId)).thenReturn(liveDetailMovie)
        val movieDetailEntities = LiveDataTestUtil.getValue(movieRepository.getDetailMovie(movieId))
        verify(movieRepository).getDetailMovie(movieId)

        assertNotNull(movieDetailEntities)
        assertEquals(movieDetailResponse.movieId, movieDetailEntities.movieId)
        assertEquals(movieDetailResponse.category, movieDetailEntities.category)
        assertEquals(movieDetailResponse.image, movieDetailEntities.image)
        assertEquals(movieDetailResponse.overview, movieDetailEntities.overview)
        assertEquals(movieDetailResponse.rating, movieDetailEntities.rating)
        assertEquals(movieDetailResponse.release, movieDetailEntities.release)
        assertEquals(movieDetailResponse.title, movieDetailEntities.title)
        assertEquals(movieDetailResponse.years, movieDetailEntities.years)
    }

    @Test
    fun testGetDetailTv() {
        val liveDetailTv = MutableLiveData<TvResponse>()
        liveDetailTv.value = tvDetailResponse

        `when`(movieRepository.getDetailTv(tvId)).thenReturn(liveDetailTv)
        val tvDetailEntities = LiveDataTestUtil.getValue(movieRepository.getDetailTv(tvId))
        verify(movieRepository).getDetailTv(tvId)

        assertNotNull(tvDetailEntities)
        assertEquals(tvDetailResponse.movieId, tvDetailEntities.movieId)
        assertEquals(tvDetailResponse.category, tvDetailEntities.category)
        assertEquals(tvDetailResponse.image, tvDetailEntities.image)
        assertEquals(tvDetailResponse.overview, tvDetailEntities.overview)
        assertEquals(tvDetailResponse.rating, tvDetailEntities.rating)
        assertEquals(tvDetailResponse.release, tvDetailEntities.release)
        assertEquals(tvDetailResponse.title, tvDetailEntities.title)
        assertEquals(tvDetailResponse.years, tvDetailEntities.years)
    }
}