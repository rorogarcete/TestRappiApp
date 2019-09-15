package com.prestosoftware.test.rappi.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prestosoftware.test.rappi.model.Movie

/**
 * Interface for database access for Movie related operations.
 */
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Query("SELECT * FROM movies where id=:id")
    fun findById(id: Int): LiveData<Movie>

    @Query("SELECT * FROM movies")
    fun findAll(): LiveData<List<Movie>>

}
