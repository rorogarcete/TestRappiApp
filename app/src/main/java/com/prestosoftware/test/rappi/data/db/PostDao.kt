package com.prestosoftware.test.rappi.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.prestosoftware.test.rappi.models.entity.Post

/**
 * Interface for database access for Post related operations.
 */
@Dao
interface PostDao {
    @Query("SELECT * FROM POST")
    fun getPostList(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<Post>)
}
