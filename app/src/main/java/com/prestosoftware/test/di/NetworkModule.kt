package com.prestosoftware.test.di

import androidx.annotation.NonNull
import com.prestosoftware.test.TestApplication
import com.prestosoftware.test.TestApplication.Companion.IS_POST_SERVICE_ON
import com.prestosoftware.test.rappi.data.api.MovieService
import com.prestosoftware.test.reign.data.PostService
import com.prestosoftware.test.rappi.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides
  @Singleton
  fun provideHttpClient(): OkHttpClient {
//      val httpCacheDirectory = File(TestRappiApplication.application.cacheDir, "cache")
//      val cacheSize: Long = 10 * 1024 * 1024 // 10 MiB
//      val cache = Cache(httpCacheDirectory, cacheSize)
//      val requestInterceptor = RequestInterceptor()

      return OkHttpClient.Builder()
        //.addInterceptor(RequestInterceptor())
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        //.cache(cache)
        .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(@NonNull okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl(
        if (IS_POST_SERVICE_ON)  {
          TestApplication.POST_API_URL
        } else {
          TestApplication.API_URL
        }
      )
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(LiveDataCallAdapterFactory())
      .build()
  }

  @Provides
  @Singleton
  fun provideMovieService(@NonNull retrofit: Retrofit): MovieService {
    return retrofit.create(MovieService::class.java)
  }

  @Provides
  @Singleton
  fun providePostService(@NonNull retrofit: Retrofit): PostService {
    return retrofit.create(PostService::class.java)
  }
}
