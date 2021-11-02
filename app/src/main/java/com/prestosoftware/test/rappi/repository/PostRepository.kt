package com.prestosoftware.test.rappi.repository

import androidx.lifecycle.LiveData
import com.prestosoftware.test.rappi.data.api.ApiResponse
import com.prestosoftware.test.rappi.data.api.PostService
import com.prestosoftware.test.rappi.data.db.PostDao
import com.prestosoftware.test.rappi.data.mappers.PostResponseMapper
import com.prestosoftware.test.rappi.models.Resource
import com.prestosoftware.test.rappi.data.response.PostResponse
import com.prestosoftware.test.rappi.models.entity.Post
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(
    private val postService: PostService,
    private val postDao: PostDao
): Repository {

    fun loadPost(): LiveData<Resource<List<Post>>> {
        return object : NetworkBoundRepository<List<Post>, PostResponse, PostResponseMapper>() {
            override fun saveFetchData(items: PostResponse) {
                items.hits?.let { postDao.insertAll(it) }
            }

            override fun shouldFetch(data: List<Post>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Post>> {
                return postDao.getPostList()
            }

            override fun fetchService(): LiveData<ApiResponse<PostResponse>> {
                return postService.getPost()
            }

            override fun mapper(): PostResponseMapper {
                return PostResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                  Timber.d("Load Post Failed $message")
            }

        }.asLiveData()
    }
}
