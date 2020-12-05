package com.example.moviecatalogue.ui.movies

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class MovieViewModelTest : TestCase() {

    private lateinit var viewModel: MovieViewModel

    @Before
    override fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun testGetMovies() {
        val movieEntity = viewModel.getMovies()
        assertNotNull(movieEntity)
        assertEquals(10, movieEntity.size)
    }
}