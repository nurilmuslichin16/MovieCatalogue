package com.example.moviecatalogue.ui.detail

import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.utils.DataDummy
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = DataDummy.generateDummyMovie()[0]
    private val movieId = dummyMovie.movieId

    private val dummyTv = DataDummy.generateDummyTv()[0]
    private val tvId = dummyTv.movieId

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun testGetMovie() {
        `when`(movieRepository.getAllMovies()).thenReturn(DataDummy.generateDummyMovie() as ArrayList<MovieEntity>)
        viewModel.setSelectedMovie(movieId)
        val movieEntity = viewModel.getMovie()
        verify(movieRepository).getAllMovies()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.category, movieEntity.category)
        assertEquals(dummyMovie.image, movieEntity.image)
        assertEquals(dummyMovie.overview, movieEntity.overview)
        assertEquals(dummyMovie.rating, movieEntity.rating)
        assertEquals(dummyMovie.release, movieEntity.release)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.years, movieEntity.years)
    }

    @Test
    fun testGetTv() {
        `when`(movieRepository.getAllTv()).thenReturn(DataDummy.generateDummyTv() as ArrayList<MovieEntity>)
        viewModel.setSelectedMovie(tvId)
        val tvEntity = viewModel.getTv()
        verify(movieRepository).getAllTv()
        assertNotNull(tvEntity)
        assertEquals(dummyTv.movieId, tvEntity.movieId)
        assertEquals(dummyTv.category, tvEntity.category)
        assertEquals(dummyTv.image, tvEntity.image)
        assertEquals(dummyTv.overview, tvEntity.overview)
        assertEquals(dummyTv.rating, tvEntity.rating)
        assertEquals(dummyTv.release, tvEntity.release)
        assertEquals(dummyTv.title, tvEntity.title)
        assertEquals(dummyTv.years, tvEntity.years)
    }
}