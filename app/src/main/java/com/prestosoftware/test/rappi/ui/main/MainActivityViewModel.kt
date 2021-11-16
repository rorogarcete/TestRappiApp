package com.prestosoftware.test.rappi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.prestosoftware.test.TestApplication
import com.prestosoftware.test.rappi.models.Resource
import com.prestosoftware.test.rappi.models.entity.Movie
import com.prestosoftware.test.rappi.repository.MovieRepository
import com.prestosoftware.test.rappi.util.AbsentLiveData
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var moviePopularLiveData: MutableLiveData<Int> = MutableLiveData()
    val moviePopularListLiveData: LiveData<Resource<List<Movie>>> = moviePopularLiveData.switchMap {
        moviePopularLiveData.value?.let {
            movieRepository.loadMovies(TestApplication.CATEGORY_POPULAR, it)
        } ?: AbsentLiveData.create()
    }

    private var movieTopLiveData: MutableLiveData<Int> = MutableLiveData()
    val movieTopListLiveData: LiveData<Resource<List<Movie>>> = movieTopLiveData.switchMap {
        movieTopLiveData.value?.let {
            movieRepository.loadMovies(TestApplication.CATEGORY_TOP, it)
        } ?: AbsentLiveData.create()
    }

    private var movieUpcomingLiveData: MutableLiveData<Int> = MutableLiveData()
    val movieUpcomingListLiveData: LiveData<Resource<List<Movie>>> = movieUpcomingLiveData.switchMap {
        movieUpcomingLiveData.value?.let {
            movieRepository.loadMovies(TestApplication.CATEGORY_UPCOMING, it)
        } ?: AbsentLiveData.create()
    }

    fun getMoviePopularListValues() = moviePopularListLiveData.value
    fun postMoviePopularPage(page: Int) = moviePopularLiveData.postValue(page)

    fun getMovieTopListValues() = movieTopListLiveData.value
    fun postMovieTopPage(page: Int) = movieTopLiveData.postValue(page)

    fun getMovieUpcomingListValues() = movieUpcomingListLiveData.value
    fun postMovieUpcomingPage(page: Int) = movieUpcomingLiveData.postValue(page)
}
