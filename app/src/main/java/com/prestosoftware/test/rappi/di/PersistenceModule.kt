package com.prestosoftware.test.rappi.di

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.prestosoftware.test.rappi.TestRappiApplication
import com.prestosoftware.test.rappi.data.db.MovieDao
import com.prestosoftware.test.rappi.data.db.PostDao
import com.prestosoftware.test.rappi.data.db.TestDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

  @Provides
  @Singleton
  fun provideDatabase(@NonNull application: Application): TestDb {
    return Room
      .databaseBuilder(application, TestDb::class.java, TestRappiApplication.BD_NAME)
      .allowMainThreadQueries()
      .build()
  }

  @Provides
  @Singleton
  fun provideMovieDao(@NonNull database: TestDb): MovieDao {
    return database.movieDao()
  }

  @Provides
  @Singleton
  fun providePostDao(@NonNull database: TestDb): PostDao {
    return database.postDao()
  }

}
