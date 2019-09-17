package com.prestosoftware.test.rappi.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.prestosoftware.test.rappi.TestRappiApplication
import com.prestosoftware.test.rappi.data.api.ApiUtil
import com.prestosoftware.test.rappi.data.api.MovieService
import com.prestosoftware.test.rappi.data.db.MovieDao
import com.prestosoftware.test.rappi.data.response.MovieListResponse
import com.prestosoftware.test.rappi.models.Resource
import com.prestosoftware.test.rappi.models.entity.Movie
import com.prestosoftware.test.rappi.repository.MovieRepository
import com.prestosoftware.test.rappi.ui.main.MainActivityViewModel
import com.prestosoftware.test.rappi.helpers.MockTestUtil
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainActivityViewModelTest {

  private lateinit var viewModel: MainActivityViewModel
  private lateinit var movieRepository: MovieRepository

  private val movieDao = mock<MovieDao>()
  private val movieService = mock<MovieService>()

  @Rule
  @JvmField
  val instantExecutorRule = InstantTaskExecutorRule()

  @Before
  fun init() {
    movieRepository = MovieRepository(movieService, movieDao)
    viewModel = MainActivityViewModel(movieRepository)
  }

  @Test
  fun loadMovieList() {
    val loadFromDB = MutableLiveData<List<Movie>>()
    whenever(movieDao.getMovieList(1)).thenReturn(loadFromDB)

    val mockResponse = MovieListResponse(1,  100, 10, emptyList())
    val call = ApiUtil.successCall(mockResponse)
    whenever(movieService.getMoviesByCategory(TestRappiApplication.CATEGORY_POPULAR,1)).thenReturn(call)

    val data = viewModel.moviePopularListLiveData
    val observer = mock<Observer<Resource<List<Movie>>>>()
    data.observeForever(observer)

    viewModel.postMoviePopularPage(1)
    verify(movieDao).getMovieList(1)
    verifyNoMoreInteractions(movieService)

    val mockMovieList = ArrayList<Movie>()
    mockMovieList.add(MockTestUtil.mockMovie())
    loadFromDB.postValue(mockMovieList)
    verify(observer).onChanged(
        Resource.success(viewModel.getMoviePopularListValues()!!.data, false)
    )
  }
}
