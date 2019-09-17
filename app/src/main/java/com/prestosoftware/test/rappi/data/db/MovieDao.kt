package com.prestosoftware.test.rappi.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.prestosoftware.test.rappi.models.entity.Movie

/**
 * Interface for database access for Movie related operations.
 */
@Dao
interface MovieDao {
    @Query("SELECT * FROM MOVIE WHERE id = :id")
    fun getMovie(id: Int): Movie

    @Query("SELECT * FROM Movie WHERE page = :page")
    fun getMovieList(page: Int): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Update
    fun updateMovie(movie: Movie)

}
