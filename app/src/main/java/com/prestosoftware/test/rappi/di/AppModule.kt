package com.prestosoftware.test.rappi.di

import android.app.Application
import androidx.room.Room
import com.prestosoftware.test.rappi.TestRappiApplication
import com.prestosoftware.test.rappi.data.api.MovieService
import com.prestosoftware.test.rappi.data.api.RequestInterceptor
import com.prestosoftware.test.rappi.data.db.MovieDb
import com.prestosoftware.test.rappi.data.db.MovieDao
import com.prestosoftware.test.rappi.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient {
        val httpCacheDirectory = File(TestRappiApplication.application.cacheDir, "cache")
        val cacheSize: Long = 10 * 1024 * 1024 // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)
        val requestInterceptor = RequestInterceptor()

        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .cache(cache)
            .build()
    }

    @Singleton
    @Provides
    fun provideService(): MovieService {
        return Retrofit.Builder()
            .baseUrl(TestRappiApplication.API_URL)
            .client(provideClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(MovieService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): MovieDb {
        return Room
            .databaseBuilder(app, MovieDb::class.java, TestRappiApplication.BD_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(db: MovieDb): MovieDao {
        return db.movieDao()
    }
}
