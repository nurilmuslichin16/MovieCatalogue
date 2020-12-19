package com.example.moviecatalogue.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<MovieResponse>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun testGetMovies() {
        val dummyMovies = DataDummy.generateDummyResponseMovie()
        val movies = MutableLiveData<List<MovieResponse>>()
        movies.value = dummyMovies

        `when`(movieRepository.getAllMovies()).thenReturn(movies)
        val movieResponse = viewModel.getMovies().value
        verify(movieRepository).getAllMovies()
        assertNotNull(movieResponse)
        assertEquals(5, movieResponse?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun testEmptyMovie() {
        val emptyEntity = viewModel.getEmptyMovie()
        assertNotNull(emptyEntity)
        assertEquals(0, emptyEntity.size)
    }
}