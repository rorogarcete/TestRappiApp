package com.prestosoftware.test.rappi.data.response

import com.google.gson.annotations.SerializedName
import com.prestosoftware.test.rappi.models.entity.Movie

data class MovieListResponse(
    @SerializedName("page") val page: Int = 1,
    @SerializedName("total_results") val totalResults: Int = 0,
    @SerializedName("total_pages") val totalPages: Int = 0,
    @SerializedName("results") val results:  List<Movie>? = listOf()
) : NetworkResponseModel
