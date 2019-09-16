package com.prestosoftware.test.rappi.data.response

data class ErrorEnvelope(
  val status_code: Int,
  val status_message: String,
  val success: Boolean
)
