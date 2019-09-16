package com.prestosoftware.test.rappi.data.mappers

import com.prestosoftware.test.rappi.data.response.VideoListResponse

class VideoResponseMapper : NetworkResponseMapper<VideoListResponse> {
  override fun onLastPage(response: VideoListResponse): Boolean {
    return true
  }
}
