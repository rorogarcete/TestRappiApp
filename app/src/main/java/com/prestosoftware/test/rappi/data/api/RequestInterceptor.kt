package com.prestosoftware.test.rappi.data.api

import com.prestosoftware.test.rappi.TestRappiApplication
import okhttp3.Interceptor
import okhttp3.Response

internal class RequestInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val originalUrl = originalRequest.url()
    val url = originalUrl.newBuilder()
      .addQueryParameter("api_key", TestRappiApplication.API_KEY)
      .addQueryParameter("language", TestRappiApplication.API_LG)
      .build()

    val requestBuilder = originalRequest.newBuilder().url(url)
    val request = requestBuilder.build()
    return chain.proceed(request)
  }
}
