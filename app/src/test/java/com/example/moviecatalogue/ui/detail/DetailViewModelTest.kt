package com.example.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.MovieRepository
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.local.entity.RMovieEntity
import com.example.moviecatalogue.data.source.local.entity.RTvEntity
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TvResponse
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
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = Resource.success(DataDummy.generateDummyResponseMovie()[0])
    private val movieId = dummyMovie.data?.movieId as Int

    private val dummyTv = Resource.success(DataDummy.generateDummyResponseTv()[0])
    private val tvId = dummyTv.data?.movieId as Int

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<RMovieEntity>>

    @Mock
    private lateinit var tvObserver: Observer<Resource<RTvEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun testGetMovie() {
        val movie = MutableLiveData<Resource<RMovieEntity>>()
        movie.value = dummyMovie

        `when`(movieRepository.getDetailMovie(movieId)).thenReturn(movie)
        viewModel.setSelectedMovie(movieId)

        viewModel.movieDetail.observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun testGetTv() {
        val tv = MutableLiveData<Resource<RTvEntity>>()
        tv.value = dummyTv

        `when`(movieRepository.getDetailTv(tvId)).thenReturn(tv)
        viewModel.setSelectedMovie(tvId)

        viewModel.tvDetail.observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyTv)
    }
}