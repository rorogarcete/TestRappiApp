package com.prestosoftware.test.rappi.models.entity

import android.os.Parcelable
import androidx.room.Entity
import com.prestosoftware.test.rappi.models.Keyword
import com.prestosoftware.test.rappi.models.Video
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = [("id")])
data class Movie(
    val id: Int,
    val title: String,
    var page: Int,
    var category: String,
    val popularity: Float,
    val poster_path: String?,
    val adult: Boolean,
    val overview: String,
    val release_date: String,
    var keywords: List<Keyword>? = ArrayList(),
    var videos: List<Video>? = ArrayList(),
    val genre_ids: List<Int>,
    val original_title: String,
    val original_language: String,
    val backdrop_path: String?,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Float
) : Parcelable