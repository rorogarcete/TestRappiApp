package com.prestosoftware.test.rappi.data.mappers

import com.prestosoftware.test.rappi.data.response.PostResponse

class PostResponseMapper : NetworkResponseMapper<PostResponse> {
  override fun onLastPage(response: PostResponse): Boolean {
    return true
  }
}
