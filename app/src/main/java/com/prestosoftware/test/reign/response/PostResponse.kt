package com.prestosoftware.test.rappi.data.response

import com.google.gson.annotations.SerializedName
import com.prestosoftware.test.reign.models.Post

data class PostResponse(
    @SerializedName("hits") val hits: List<PostModel>? = emptyList()
) : NetworkResponseModel

data class PostModel(
    @SerializedName("objectID") val objectID: String? = "",
    @SerializedName("created_at") val createdAt: String? = "",
    @SerializedName("author") val author: String? = "",
    @SerializedName("story_title", alternate = ["title", "story_title.value"]) val title: String? = "",
    @SerializedName("story_url", alternate = ["story_url.value"]) val url: String? = ""
)

fun PostModel.mapToEntity(): Post {
    return Post(
        this.objectID.orEmpty(),
        this.createdAt.orEmpty(),
        this.author.orEmpty(),
        this.title.orEmpty(),
        this.url.orEmpty()
    )
}