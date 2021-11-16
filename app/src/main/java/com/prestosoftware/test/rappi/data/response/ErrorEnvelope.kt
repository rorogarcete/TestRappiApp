package com.prestosoftware.test.rappi.data.response

data class ErrorEnvelope(
    val statusCode: Int,
    val statusMessage: String,
    val success: Boolean
)
