package com.prestosoftware.test.rappi.data.api

import okhttp3.Interceptor
import okhttp3.Response

internal class RequestInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val originalUrl = originalRequest.url()
    val url = originalUrl.newBuilder()
      .addQueryParameter("api_key","2805b177bde136e05a6502ec65f855f0")
      .build()

    val requestBuilder = originalRequest.newBuilder().url(url)
    val request = requestBuilder.build()
    return chain.proceed(request)
  }
}
