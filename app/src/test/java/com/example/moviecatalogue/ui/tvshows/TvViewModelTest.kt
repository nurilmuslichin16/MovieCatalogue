package com.example.moviecatalogue.ui.tvshows

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class TvViewModelTest : TestCase() {

    private lateinit var viewModel: TvViewModel

    @Before
    override fun setUp() {
        viewModel = TvViewModel()
    }

    @Test
    fun testGetTv() {
        val tvEntity = viewModel.getTv()
        assertNotNull(tvEntity)
        assertEquals(10, tvEntity.size)
    }
}