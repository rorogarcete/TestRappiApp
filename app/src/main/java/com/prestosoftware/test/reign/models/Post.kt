package com.prestosoftware.test.reign.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = [("objectID")])
data class Post(
    val objectID: String,
    val createdAt: String,
    val author: String,
    val title: String,
    val url: String
) : Parcelable