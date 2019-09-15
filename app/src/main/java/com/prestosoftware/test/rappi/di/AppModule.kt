package com.prestosoftware.test.rappi.di

import android.app.Application
import androidx.room.Room
import com.prestosoftware.test.rappi.data.api.MovieService
import com.prestosoftware.test.rappi.data.db.MovieDb
import com.prestosoftware.test.rappi.data.db.dao.MovieDao
import com.prestosoftware.test.rappi.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideService(): MovieService {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(MovieService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): MovieDb {
        return Room
            .databaseBuilder(app, MovieDb::class.java, "movie.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(db: MovieDb): MovieDao {
        return db.movieDao()
    }
}
