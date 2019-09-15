package com.prestosoftware.test.rappi.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey private val id: Long,
    private val title: String,
    private val overview: String,
    private val popularity: Double,
    private val voteCount: Double,
    private val video: Boolean,
    private val posterPatch: String,
    private val adult: Boolean,
    private val backdropPath: String,
    private val originalLanguage: String,
    private val originalTitle: String
)
