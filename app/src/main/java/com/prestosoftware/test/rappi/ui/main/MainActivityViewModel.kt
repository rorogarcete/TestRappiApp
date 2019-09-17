package com.prestosoftware.test.rappi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.prestosoftware.test.rappi.TestRappiApplication
import com.prestosoftware.test.rappi.models.Resource
import com.prestosoftware.test.rappi.models.entity.Movie
import com.prestosoftware.test.rappi.repository.MovieRepository
import com.prestosoftware.test.rappi.util.AbsentLiveData
import javax.inject.Inject

class MainActivityViewModel @Inject
  constructor(private val movieRepository: MovieRepository): ViewModel() {

  private var moviePopularLiveData: MutableLiveData<Int> = MutableLiveData()
  val moviePopularListLiveData: LiveData<Resource<List<Movie>>>

  private var movieTopLiveData: MutableLiveData<Int> = MutableLiveData()
  val movieTopListLiveData: LiveData<Resource<List<Movie>>>

  private var movieUpcomingLiveData: MutableLiveData<Int> = MutableLiveData()
  val movieUpcomingListLiveData: LiveData<Resource<List<Movie>>>

  init {
    moviePopularListLiveData = moviePopularLiveData.switchMap {
      moviePopularLiveData.value?.let { movieRepository.loadMovies(TestRappiApplication.CATEGORY_POPULAR, it) }
        ?: AbsentLiveData.create()
    }

    movieTopListLiveData = movieTopLiveData.switchMap {
      movieTopLiveData.value?.let { movieRepository.loadMovies(TestRappiApplication.CATEGORY_TOP, it) }
        ?: AbsentLiveData.create()
    }

    movieUpcomingListLiveData = movieUpcomingLiveData.switchMap {
      movieUpcomingLiveData.value?.let { movieRepository.loadMovies(TestRappiApplication.CATEGORY_UPCOMING, it) }
        ?: AbsentLiveData.create()
    }
  }

  fun getMovieListValues() = moviePopularListLiveData.value
  fun postMoviePage(page: Int) = moviePopularLiveData.postValue(page)

  fun getTvListValues() = movieTopListLiveData.value
  fun postTvPage(page: Int) = movieTopLiveData.postValue(page)

  fun getPeopleValues() = movieUpcomingListLiveData.value
  fun postPeoplePage(page: Int) = movieUpcomingLiveData.postValue(page)
}
