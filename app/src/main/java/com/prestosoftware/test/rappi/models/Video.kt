package com.prestosoftware.test.rappi.models

//@Parcelize : Parcelable
data class Video(
  val id: String,
  val name: String,
  val site: String,
  val key: String,
  val size: Int,
  val type: String
)