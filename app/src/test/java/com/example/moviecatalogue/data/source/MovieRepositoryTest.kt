package com.example.moviecatalogue.data.source

import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.ui.FakeMovieRepository
import com.example.moviecatalogue.utils.DataDummy
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class MovieRepositoryTest {

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val movieResponse = DataDummy.generateRemoteDummyMovie()
    private val movieId = movieResponse[0].movieId

    private val tvResponse = DataDummy.generateRemoteDummyTv()
    private val tvId = movieResponse[0].movieId

    @Test
    fun testGetAllMovies() {
        `when`<List<MovieResponse>>(remote.getAllMovies()).thenReturn(movieResponse)
        val movieEntities = movieRepository.getAllMovies()
        verify<RemoteDataSource>(remote).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun testGetAllTv() {
        `when`<List<MovieResponse>>(remote.getAllTv()).thenReturn(tvResponse)
        val tvEntities = movieRepository.getAllTv()
        verify<RemoteDataSource>(remote).getAllTv()
        assertNotNull(tvEntities)
        assertEquals(movieResponse.size.toLong(), tvEntities.size.toLong())
    }
}