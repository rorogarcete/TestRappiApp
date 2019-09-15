package com.prestosoftware.test.rappi.data.api

import androidx.lifecycle.LiveData
import com.prestosoftware.test.rappi.data.Movie
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * REST API access points
 */
interface MovieService {

    // Get Movies Popular
    @GET("movie/popular")
    fun getMoviesPopular(): LiveData<MovieResponse>

    // Get Movies Top
    @GET("movie/top_rated")
    fun getMoviesTop(): LiveData<MovieResponse>

    //Get Movies Upcoming
    @GET("movie/upcoming")
    fun getMoviesUpcoming(): LiveData<MovieResponse>

    @GET("movie/{movieId}")
    fun getMovieDetail(@Path("movieId") movieId: String): LiveData<Movie>

}
