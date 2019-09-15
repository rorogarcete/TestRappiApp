package com.prestosoftware.test.rappi.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prestosoftware.test.rappi.data.db.entity.MovieEntity

/**
 * Interface for database access for User related operations.
 */
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieEntity)

    @Query("SELECT * FROM id WHERE id = :id")
    fun findByFilter(login: String): LiveData<MovieEntity>
}
