package com.prestosoftware.test.rappi.models.network

import com.prestosoftware.test.rappi.models.Video

data class VideoListResponse(
  val id: Int,
  val results: List<Video>
) : NetworkResponseModel
