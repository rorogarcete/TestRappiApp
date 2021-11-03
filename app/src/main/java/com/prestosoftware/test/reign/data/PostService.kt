package com.prestosoftware.test.reign.data

import androidx.lifecycle.LiveData
import com.prestosoftware.test.rappi.data.api.Api.QUERY_PARAM
import com.prestosoftware.test.rappi.data.api.ApiResponse
import com.prestosoftware.test.rappi.data.response.PostResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * REST API access points for post hits
 */
interface PostService {

    // Get Posts hits
    @GET("search_by_date")
    fun getPost(@Query("query") query: String = QUERY_PARAM):
        LiveData<ApiResponse<PostResponse>>
}
