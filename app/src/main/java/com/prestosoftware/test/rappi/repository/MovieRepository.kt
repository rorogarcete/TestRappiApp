package com.prestosoftware.test.rappi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.prestosoftware.test.rappi.data.api.ApiResponse
import com.prestosoftware.test.rappi.data.api.MovieService
import com.prestosoftware.test.rappi.data.db.MovieDao
import com.prestosoftware.test.rappi.data.mappers.KeywordResponseMapper
import com.prestosoftware.test.rappi.data.mappers.MovieResponseMapper
import com.prestosoftware.test.rappi.data.mappers.VideoResponseMapper
import com.prestosoftware.test.rappi.models.Keyword
import com.prestosoftware.test.rappi.models.Resource
import com.prestosoftware.test.rappi.models.Video
import com.prestosoftware.test.rappi.data.response.KeywordListResponse
import com.prestosoftware.test.rappi.data.response.MovieListResponse
import com.prestosoftware.test.rappi.data.response.VideoListResponse
import com.prestosoftware.test.rappi.models.entity.Movie
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject
constructor(val service: MovieService, val movieDao: MovieDao) : Repository {

  init {
    Timber.d("Injection MovieRepository")
  }

  fun loadMovies(category: String, page: Int): LiveData<Resource<List<Movie>>> {
    return object : NetworkBoundRepository<List<Movie>, MovieListResponse, MovieResponseMapper>() {
      override fun saveFetchData(items: MovieListResponse) {
        for (item in items.results) {
          item.page = page
        }
        movieDao.insertAll(movies = items.results)
      }

      override fun shouldFetch(data: List<Movie>?): Boolean {
        return data == null || data.isEmpty()
      }

      override fun loadFromDb(): LiveData<List<Movie>> {
        return movieDao.getMovieList(page_ = page)
      }

      override fun fetchService(): LiveData<ApiResponse<MovieListResponse>> {
        return service.getMoviesByCategory(category = category, page = page)
      }

      override fun mapper(): MovieResponseMapper {
        return MovieResponseMapper()
      }

      override fun onFetchFailed(message: String?) {
        Timber.d("onFetchFailed $message")
      }
    }.asLiveData()
  }

  fun loadKeywordList(id: Int): LiveData<Resource<List<Keyword>>> {
    return object : NetworkBoundRepository<List<Keyword>, KeywordListResponse, KeywordResponseMapper>() {
      override fun saveFetchData(items: KeywordListResponse) {
        val movie = movieDao.getMovie(id_ = id)
        movie.keywords = items.keywords
        movieDao.updateMovie(movie = movie)
      }

      override fun shouldFetch(data: List<Keyword>?): Boolean {
        return data == null || data.isEmpty()
      }

      override fun loadFromDb(): LiveData<List<Keyword>> {
        val movie = movieDao.getMovie(id_ = id)
        val data: MutableLiveData<List<Keyword>> = MutableLiveData()
        data.value = movie.keywords
        return data
      }

      override fun fetchService(): LiveData<ApiResponse<KeywordListResponse>> {
        return service.fetchKeywords(id = id)
      }

      override fun mapper(): KeywordResponseMapper {
        return KeywordResponseMapper()
      }

      override fun onFetchFailed(message: String?) {
        Timber.d("onFetchFailed : $message")
      }
    }.asLiveData()
  }

  fun loadVideoList(id: Int): LiveData<Resource<List<Video>>> {
    return object : NetworkBoundRepository<List<Video>, VideoListResponse, VideoResponseMapper>() {
      override fun saveFetchData(items: VideoListResponse) {
        val movie = movieDao.getMovie(id_ = id)
        movie.videos = items.results
        movieDao.updateMovie(movie = movie)
      }

      override fun shouldFetch(data: List<Video>?): Boolean {
        return data == null || data.isEmpty()
      }

      override fun loadFromDb(): LiveData<List<Video>> {
        val movie = movieDao.getMovie(id_ = id)
        val data: MutableLiveData<List<Video>> = MutableLiveData()
        data.value = movie.videos
        return data
      }

      override fun fetchService(): LiveData<ApiResponse<VideoListResponse>> {
        return service.fetchVideos(id = id)
      }

      override fun mapper(): VideoResponseMapper {
        return VideoResponseMapper()
      }

      override fun onFetchFailed(message: String?) {
        Timber.d("onFetchFailed : $message")
      }
    }.asLiveData()
  }
}
