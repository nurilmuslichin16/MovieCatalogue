package com.example.moviecatalogue.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.MovieRepository
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
class TvViewModelTest {

    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<TvResponse>>

    @Before
    fun setUp() {
        viewModel = TvViewModel(movieRepository)
    }

    @Test
    fun testGetTv() {
        val dummyTv = DataDummy.generateDummyResponseTv()
        val tv = MutableLiveData<List<TvResponse>>()
        tv.value = dummyTv

        `when`(movieRepository.getAllTv()).thenReturn(tv)
        val tvResponse = viewModel.getTv().value
        verify(movieRepository).getAllTv()
        assertNotNull(tvResponse)
        assertEquals(5, tvResponse?.size)

        viewModel.getTv().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }

    @Test
    fun testEmptyTv() {
        val emptyEntity = viewModel.getEmptyTv()
        assertNotNull(emptyEntity)
        assertEquals(0, emptyEntity.size)
    }
}