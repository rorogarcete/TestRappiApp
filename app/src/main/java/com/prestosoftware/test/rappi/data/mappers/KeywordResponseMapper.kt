package com.prestosoftware.test.rappi.data.mappers

import com.prestosoftware.test.rappi.data.response.KeywordListResponse

class KeywordResponseMapper : NetworkResponseMapper<KeywordListResponse> {
  override fun onLastPage(response: KeywordListResponse): Boolean {
    return true
  }
}
