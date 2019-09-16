package com.prestosoftware.test.rappi.data.api

import androidx.lifecycle.LiveData
import com.prestosoftware.test.rappi.models.entity.Movie
import com.prestosoftware.test.rappi.data.response.KeywordListResponse
import com.prestosoftware.test.rappi.data.response.MovieListResponse
import com.prestosoftware.test.rappi.data.response.VideoListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * REST API access points
 */
interface MovieService {

    // Get Movies Popular
    @GET("movie/{category}")
    fun getMoviesByCategory(@Path("category") category: String, @Query("page") page: Int): LiveData<ApiResponse<MovieListResponse>>

    @GET("movie/{movieId}")
    fun getMovieDetail(@Path("movieId") movieId: String): LiveData<Movie>

    /**
     * Get the keywords that have been added to a movie.
     * @param [id] Specify the id of movie id.
     * @return [KeywordListResponse] response
     */
    @GET("/3/movie/{movie_id}/keywords")
    fun fetchKeywords(@Path("movie_id") id: Int): LiveData<ApiResponse<KeywordListResponse>>

    /**
     * Get the videos that have been added to a movie.
     * @param [id] Specify the id of movie id.
     * @return [VideoListResponse] response
     */
    @GET("/3/movie/{movie_id}/videos")
    fun fetchVideos(@Path("movie_id") id: Int): LiveData<ApiResponse<VideoListResponse>>

}
