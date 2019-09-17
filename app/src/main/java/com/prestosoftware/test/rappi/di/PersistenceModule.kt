package com.prestosoftware.test.rappi.di

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.prestosoftware.test.rappi.data.db.MovieDao
import com.prestosoftware.test.rappi.data.db.MovieDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

  @Provides
  @Singleton
  fun provideDatabase(@NonNull application: Application): MovieDb {
    return Room
      .databaseBuilder(application, MovieDb::class.java, "movies")
      .allowMainThreadQueries()
      .build()
  }

  @Provides
  @Singleton
  fun provideMovieDao(@NonNull database: MovieDb): MovieDao {
    return database.movieDao()
  }

}
