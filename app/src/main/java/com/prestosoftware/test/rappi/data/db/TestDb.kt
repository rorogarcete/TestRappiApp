package com.prestosoftware.test.rappi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.prestosoftware.test.rappi.models.entity.Movie
import com.prestosoftware.test.reign.models.Post
import com.prestosoftware.test.rappi.util.IntegerListConverter
import com.prestosoftware.test.rappi.util.KeywordListConverter
import com.prestosoftware.test.rappi.util.StringListConverter
import com.prestosoftware.test.rappi.util.VideoListConverter
import com.prestosoftware.test.reign.data.dao.PostDao

/**
 * Movie database description.
 */
@Database(
    entities =
    [
        Movie::class,
        Post::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    value = [
        (StringListConverter::class),
        (IntegerListConverter::class),
        (KeywordListConverter::class),
        (VideoListConverter::class)
    ]
)
abstract class TestDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun postDao(): PostDao
}
