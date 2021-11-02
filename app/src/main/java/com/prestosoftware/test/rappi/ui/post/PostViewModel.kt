package com.prestosoftware.test.rappi.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.prestosoftware.test.rappi.models.Resource
import com.prestosoftware.test.rappi.models.entity.Post
import com.prestosoftware.test.rappi.repository.PostRepository
import javax.inject.Inject

class PostViewModel @Inject constructor(
	private val postRepository: PostRepository
): ViewModel() {

	val postListLiveData: LiveData<Resource<List<Post>>> = postRepository.loadPost()

}
