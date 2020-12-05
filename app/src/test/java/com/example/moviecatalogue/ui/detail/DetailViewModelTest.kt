package com.example.moviecatalogue.ui.detail

import com.example.moviecatalogue.utils.DataDummy
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class DetailViewModelTest : TestCase() {

    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = DataDummy.generateDummyMovie()[0]
    private val movieId = dummyMovie.movieId

    private val dummyTv = DataDummy.generateDummyTv()[0]
    private val tvId = dummyTv.movieId

    @Before
    override fun setUp() {
        viewModel = DetailViewModel()
    }

    @Test
    fun testGetMovie() {
        viewModel.setSelectedMovie(movieId)
        val movieEntity = viewModel.getMovie()
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
        viewModel.setSelectedMovie(tvId)
        val tvEntity = viewModel.getTv()
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