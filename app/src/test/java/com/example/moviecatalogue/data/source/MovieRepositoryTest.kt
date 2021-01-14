package com.example.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.RMovieEntity
import com.example.moviecatalogue.data.source.local.entity.RTvEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.ui.FakeMovieRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import androidx.paging.DataSource
import com.example.moviecatalogue.utils.*
import com.example.moviecatalogue.vo.Resource

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)

    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val movieResponse = DataDummy.generateDummyResponseMovie()
    private val movieDetailResponse = DataDummy.generateDummyResponseMovie()[0]
    private val movieId = movieResponse[0].movieId

    private val tvResponse = DataDummy.generateDummyResponseTv()
    private val tvDetailResponse = DataDummy.generateDummyResponseTv()[0]
    private val tvId = movieResponse[0].movieId

    @Test
    fun testGetAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, RMovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        movieRepository.getAllMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(movieResponse))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun testGetAllTv() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, RTvEntity>
        `when`(local.getAllTv()).thenReturn(dataSourceFactory)
        movieRepository.getAllTv()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(tvResponse))
        verify(local).getAllTv()
        assertNotNull(tvEntities.data)
        assertEquals(movieResponse.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun testGetAllFavMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, RMovieEntity>
        `when`(local.getAllFavoriteMovies()).thenReturn(dataSourceFactory)
        movieRepository.getAllFavoriteMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(movieResponse))
        verify(local).getAllFavoriteMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun testGetAllFavTv() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, RTvEntity>
        `when`(local.getAllFavoriteTv()).thenReturn(dataSourceFactory)
        movieRepository.getAllFavoriteTv()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(movieResponse))
        verify(local).getAllFavoriteTv()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun testGetDetailMovie() {
        val liveDetailMovie = MutableLiveData<RMovieEntity>()
        liveDetailMovie.value = movieDetailResponse

        `when`(local.getDetailMovie(movieId)).thenReturn(liveDetailMovie)
        val movieDetailEntities = LiveDataTestUtil.getValue(movieRepository.getDetailMovie(movieId))
        verify(local).getDetailMovie(movieId)

        assertNotNull(movieDetailEntities)
        assertEquals(movieDetailResponse.movieId, movieDetailEntities.data?.movieId)
        assertEquals(movieDetailResponse.category, movieDetailEntities.data?.category)
        assertEquals(movieDetailResponse.image, movieDetailEntities.data?.image)
        assertEquals(movieDetailResponse.overview, movieDetailEntities.data?.overview)
        assertEquals(movieDetailResponse.rating, movieDetailEntities.data?.rating)
        assertEquals(movieDetailResponse.release, movieDetailEntities.data?.release)
        assertEquals(movieDetailResponse.title, movieDetailEntities.data?.title)
        assertEquals(movieDetailResponse.years, movieDetailEntities.data?.years)
    }

    @Test
    fun testGetDetailTv() {
        val liveDetailTv = MutableLiveData<RTvEntity>()
        liveDetailTv.value = tvDetailResponse

        `when`(local.getDetailTv(tvId)).thenReturn(liveDetailTv)
        val tvDetailEntities = LiveDataTestUtil.getValue(movieRepository.getDetailTv(tvId))
        verify(local).getDetailTv(tvId)

        assertNotNull(tvDetailEntities)
        assertEquals(tvDetailResponse.movieId, tvDetailEntities.data?.movieId)
        assertEquals(tvDetailResponse.category, tvDetailEntities.data?.category)
        assertEquals(tvDetailResponse.image, tvDetailEntities.data?.image)
        assertEquals(tvDetailResponse.overview, tvDetailEntities.data?.overview)
        assertEquals(tvDetailResponse.rating, tvDetailEntities.data?.rating)
        assertEquals(tvDetailResponse.release, tvDetailEntities.data?.release)
        assertEquals(tvDetailResponse.title, tvDetailEntities.data?.title)
        assertEquals(tvDetailResponse.years, tvDetailEntities.data?.years)
    }

    @Test
    fun testSetFavoriteMovie() {
        val movie = RMovieEntity(733317,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/m10Sx3ikuXhJomFnN3OYpkBpYEc.jpg",
                "Disclosure",
                "1994",
                6.5,
                "Drama, Crime, Thriller",
                "A computer specialist is sued for sexual harassment by a former lover turned boss who initiated the act forcefully, which threatens both his career and his personal life.",
                "1994-03-10",
                false)

        local.setFavoriteMovie(movie, true)
        verify(local).setFavoriteMovie(movie, true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun testSetFavoriteTv() {
        val tv = RTvEntity(82856,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg",
                "The Simpsons",
                "1989",
                7.8,
                "Animation, Comedy, Family, Drama",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                "1989-12-16",
                false)

        local.setFavoriteTv(tv, true)
        verify(local).setFavoriteTv(tv, true)
        verifyNoMoreInteractions(local)
    }
}