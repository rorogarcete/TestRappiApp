package com.prestosoftware.test.rappi.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.prestosoftware.test.rappi.data.api.ApiUtil
import com.prestosoftware.test.rappi.data.api.MovieService
import com.prestosoftware.test.rappi.data.db.MovieDao
import com.prestosoftware.test.rappi.data.response.KeywordListResponse
import com.prestosoftware.test.rappi.data.response.VideoListResponse
import com.prestosoftware.test.rappi.helpers.MockTestUtil
import com.prestosoftware.test.rappi.models.Keyword
import com.prestosoftware.test.rappi.models.Resource
import com.prestosoftware.test.rappi.models.Video
import com.prestosoftware.test.rappi.repository.MovieRepository
import com.prestosoftware.test.rappi.ui.movie.detail.MovieDetailViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieDetailVIewModelTest {

  private lateinit var viewModel: MovieDetailViewModel
  private lateinit var repository: MovieRepository

  private val movieDao = mock<MovieDao>()
  private val service = mock<MovieService>()

  @Rule
  @JvmField
  val instantExecutorRule = InstantTaskExecutorRule()

  @Before
  fun init() {
    repository = MovieRepository(service, movieDao)
    viewModel = MovieDetailViewModel(repository)
  }

  @Test
  fun loadKeywordList() {
    val loadFromDB = MockTestUtil.mockMovie()
    whenever(movieDao.getMovie(123)).thenReturn(loadFromDB)

    val mockResponse = KeywordListResponse(123, MockTestUtil.mockKeywordList())
    val call = ApiUtil.successCall(mockResponse)
    whenever(service.fetchKeywords(123)).thenReturn(call)

    val data = repository.loadKeywordList(123)
    val observer = mock<Observer<Resource<List<Keyword>>>>()
    data.observeForever(observer)

    viewModel.postMovieId(123)
    verify(movieDao, times(3)).getMovie(123)
    verify(observer).onChanged(
        Resource.success(MockTestUtil.mockKeywordList(), true))

    val updatedMovie = MockTestUtil.mockMovie()
    updatedMovie.keywords = MockTestUtil.mockKeywordList()
    verify(movieDao).updateMovie(updatedMovie)
  }

  @Test
  fun loadVideoList() {
    val loadFromDB = MockTestUtil.mockMovie()
    whenever(movieDao.getMovie(123)).thenReturn(loadFromDB)

    val mockResponse = VideoListResponse(123, MockTestUtil.mockVideoList())
    val call = ApiUtil.successCall(mockResponse)
    whenever(service.fetchVideos(123)).thenReturn(call)

    val data = repository.loadVideoList(123)
    val observer = mock<Observer<Resource<List<Video>>>>()
    data.observeForever(observer)

    viewModel.postMovieId(123)
    verify(movieDao, times(3)).getMovie(123)
    verify(observer).onChanged(
        Resource.success(MockTestUtil.mockVideoList(), true)
    )

    val updatedMovie = MockTestUtil.mockMovie()
    updatedMovie.videos = MockTestUtil.mockVideoList()
    verify(movieDao).updateMovie(updatedMovie)
  }

}
