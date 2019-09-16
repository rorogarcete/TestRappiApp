package com.prestosoftware.test.rappi.data.mappers

import com.prestosoftware.test.rappi.data.response.MovieListResponse

class MovieResponseMapper : NetworkResponseMapper<MovieListResponse> {
  override fun onLastPage(response: MovieListResponse): Boolean {
    return response.page > response.totalPages
  }
}
