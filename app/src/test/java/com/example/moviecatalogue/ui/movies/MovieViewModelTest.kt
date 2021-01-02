package com.example.moviecatalogue.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.RMovieEntity
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.vo.Resource
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
    private lateinit var observer: Observer<Resource<PagedList<RMovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<RMovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun testGetMovies() {
        val dummyMovies = Resource.success(pagedList)
        `when`(dummyMovies.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<RMovieEntity>>>()
        movies.value = dummyMovies

        `when`(movieRepository.getAllMovies()).thenReturn(movies)
        val movieResponse = viewModel.getMovies().value?.data
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