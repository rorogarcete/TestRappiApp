package com.prestosoftware.test.rappi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prestosoftware.test.rappi.data.db.dao.MovieDao
import com.prestosoftware.test.rappi.data.db.entity.MovieEntity

/**
 * Main database description.
 */
@Database(
    entities = [
        MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDb : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}
