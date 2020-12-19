package com.example.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.MovieRepository
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TvResponse
import com.example.moviecatalogue.utils.DataDummy
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = DataDummy.generateDummyResponseMovie()[0]
    private val movieId = dummyMovie.movieId

    private val dummyTv = DataDummy.generateDummyResponseTv()[0]
    private val tvId = dummyTv.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieResponse>

    @Mock
    private lateinit var tvObserver: Observer<TvResponse>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun testGetMovie() {
        val movie = MutableLiveData<MovieResponse>()
        movie.value = dummyMovie

        `when`(movieRepository.getDetailMovie(movieId)).thenReturn(movie)
        viewModel.setSelectedMovie(movieId)

        val movieEntity = viewModel.getMovie().value as MovieResponse
        verify(movieRepository).getDetailMovie(movieId)

        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.category, movieEntity.category)
        assertEquals(dummyMovie.image, movieEntity.image)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.rating, movieEntity.rating)
        assertEquals(dummyMovie.release, movieEntity.release)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.years, movieEntity.years)

        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun testGetTv() {
        val tv = MutableLiveData<TvResponse>()
        tv.value = dummyTv

        `when`(movieRepository.getDetailTv(tvId)).thenReturn(tv)
        viewModel.setSelectedMovie(tvId)

        val tvEntity = viewModel.getTv().value as TvResponse
        verify(movieRepository).getDetailTv(tvId)

        assertNotNull(tvEntity)
        assertEquals(dummyTv.movieId, tvEntity.movieId)
        assertEquals(dummyTv.category, tvEntity.category)
        assertEquals(dummyTv.image, tvEntity.image)
        assertEquals(dummyTv.overview, tvEntity.overview)
        assertEquals(dummyTv.rating, tvEntity.rating)
        assertEquals(dummyTv.release, tvEntity.release)
        assertEquals(dummyTv.title, tvEntity.title)
        assertEquals(dummyTv.years, tvEntity.years)

        viewModel.getTv().observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyTv)
    }
}