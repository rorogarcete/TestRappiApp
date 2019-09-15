package com.prestosoftware.test.rappi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prestosoftware.test.rappi.model.Movie

/**
 * Main database description.
 */
@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDb : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}
