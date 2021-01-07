package com.example.moviecatalogue.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.RTvEntity
import com.example.moviecatalogue.data.source.remote.response.TvResponse
import com.example.moviecatalogue.ui.menu.tvshows.FavTvViewModel
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.SortUtils
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
class FavTvViewModelTest {

    private lateinit var viewModel: FavTvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<PagedList<RTvEntity>>

    @Mock
    private lateinit var pagedList: PagedList<RTvEntity>

    @Before
    fun setUp() {
        viewModel = FavTvViewModel(movieRepository)
    }

    @Test
    fun testGetTv() {
        val dummyTv = pagedList
        `when`(dummyTv.size).thenReturn(5)
        val tv = MutableLiveData<PagedList<RTvEntity>>()
        tv.value = dummyTv

        `when`(movieRepository.getAllFavoriteTv(SortUtils.NEWEST)).thenReturn(tv)
        val tvResponse = viewModel.getTv(SortUtils.NEWEST).value
        verify(movieRepository).getAllFavoriteTv(SortUtils.NEWEST)
        assertNotNull(tvResponse)
        assertEquals(5, tvResponse?.size)

        viewModel.getTv(SortUtils.NEWEST).observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }

    @Test
    fun testEmptyTv() {
        val emptyEntity = viewModel.getEmptyTv()
        assertNotNull(emptyEntity)
        assertEquals(0, emptyEntity.size)
    }
}