package com.prestosoftware.test.rappi.models.network

import com.prestosoftware.test.rappi.models.Keyword

data class KeywordListResponse(
  val id: Int,
  val keywords: List<Keyword>
) : NetworkResponseModel
