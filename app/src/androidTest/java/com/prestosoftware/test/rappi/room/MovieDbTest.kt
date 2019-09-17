package com.prestosoftware.test.rappi.room

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.prestosoftware.test.rappi.data.db.MovieDb
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class MovieDbTest {
  lateinit var db: MovieDb

  @Before
  fun initDB() {
    db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,
      MovieDb::class.java).build()
  }

  @After
  fun closeDB() {
    db.close()
  }
}
