package com.prestosoftware.test.rappi.data.response

import com.google.gson.annotations.SerializedName
import com.prestosoftware.test.rappi.models.entity.Post

data class PostResponse(
    @SerializedName("hits") val hits: List<Post>? = emptyList()
) : NetworkResponseModel