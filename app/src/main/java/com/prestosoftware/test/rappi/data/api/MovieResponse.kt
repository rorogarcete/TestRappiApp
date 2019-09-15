package com.prestosoftware.test.rappi.data.api

import com.google.gson.annotations.SerializedName
import com.prestosoftware.test.rappi.data.model.Movie

data class MovieResponse (
    val page: Int = 1,
    @SerializedName("total_results")
    val totalResults: Int = 0,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("results")
    val results:  List<Movie>
)