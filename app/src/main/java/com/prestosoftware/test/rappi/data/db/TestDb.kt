package com.prestosoftware.test.rappi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.prestosoftware.test.rappi.models.entity.Movie
import com.prestosoftware.test.rappi.util.IntegerListConverter
import com.prestosoftware.test.rappi.util.KeywordListConverter
import com.prestosoftware.test.rappi.util.VideoListConverter

/**
 * Movie database description.
 */
@Database(
    entities =
    [
        Movie::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    value = [
        IntegerListConverter::class,
        KeywordListConverter::class,
        VideoListConverter::class
    ]
)
abstract class TestDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
