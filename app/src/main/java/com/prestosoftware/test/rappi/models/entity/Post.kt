package com.prestosoftware.test.rappi.models.entity

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = [("objectID")])
data class Post(
    @SerializedName("objectID") val objectID: String,
    @SerializedName("story_title", alternate = ["title"]) val title: String? = "",
    @SerializedName("author") val author: String? = "",
    @SerializedName("created_at") val createdAt: String? = ""
) : Parcelable