package com.prestosoftware.test.reign.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.prestosoftware.test.reign.models.Post

/**
 * Interface for database access for Post related operations.
 */
@Dao
interface PostDao {
    @Query("SELECT * FROM POST")
    fun getPostList(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<Post>)

    @Delete
    fun remove(post: Post): Int
}
