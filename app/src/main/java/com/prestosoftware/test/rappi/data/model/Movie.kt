package com.prestosoftware.test.rappi.data.model

import com.google.gson.annotations.SerializedName

data class Movie (
    val id: Long,
    val title: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("vote_count")
    val voteCount: Double,
    val video: Boolean,
    val posterPatch: String,
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String
)