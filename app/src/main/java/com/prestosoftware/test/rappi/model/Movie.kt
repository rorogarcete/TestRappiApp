package com.prestosoftware.test.rappi.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"], tableName = "movies")
data class Movie (
    val id: Long,
    val title: String,
    val overview: String,
    val popularity: Double,
    @field:SerializedName("vote_count")
    val voteCount: Double,
    val video: Boolean,
    val posterPatch: String,
    val adult: Boolean,
    @field:SerializedName("backdrop_path")
    val backdropPath: String,
    @field:SerializedName("original_language")
    val originalLanguage: String,
    @field:SerializedName("original_title")
    val originalTitle: String
)