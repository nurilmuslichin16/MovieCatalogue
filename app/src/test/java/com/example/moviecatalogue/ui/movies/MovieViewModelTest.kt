package com.example.moviecatalogue.ui.movies

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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun testGetMovies() {
        `when`(movieRepository.getAllMovies()).thenReturn(DataDummy.generateDummyMovie() as ArrayList<MovieEntity>)
        val movieEntity = viewModel.getMovies()
        verify<MovieRepository>(movieRepository).getAllMovies()
        assertNotNull(movieEntity)
        assertEquals(10, movieEntity.size)
    }

    @Test
    fun testEmptyMovie() {
        val emptyEntity = viewModel.getEmptyMovie()
        assertNotNull(emptyEntity)
        assertEquals(0, emptyEntity.size)
    }
}