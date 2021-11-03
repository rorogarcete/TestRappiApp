package com.prestosoftware.test.reign.repository

import androidx.lifecycle.LiveData
import com.prestosoftware.test.rappi.data.api.ApiResponse
import com.prestosoftware.test.reign.data.PostService
import com.prestosoftware.test.reign.data.dao.PostDao
import com.prestosoftware.test.rappi.data.mappers.PostResponseMapper
import com.prestosoftware.test.rappi.models.Resource
import com.prestosoftware.test.rappi.data.response.PostResponse
import com.prestosoftware.test.rappi.data.response.mapToEntity
import com.prestosoftware.test.rappi.repository.NetworkBoundRepository
import com.prestosoftware.test.rappi.repository.Repository
import com.prestosoftware.test.reign.models.Post
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
                items.hits?.let {
                    postDao.insertAll(items.hits.map {
                        it.mapToEntity()
                    })
                }
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

    fun removePost(post: Post): Boolean {
        val result = postDao.remove(post)
        return result > 0
    }

}
