package com.prestosoftware.test.reign.ui.post

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import com.prestosoftware.test.rappi.R
import com.prestosoftware.test.rappi.databinding.ActivityPostBinding
import com.prestosoftware.test.reign.models.Post
import com.prestosoftware.test.reign.ui.post.detail.PostDetailActivity
import com.prestosoftware.test.rappi.util.compose.ViewModelActivity

class PostActivity: ViewModelActivity(), PostViewHolder.Delegate  {

    private val viewModelPost by viewModel<PostViewModel>()

    private val binding by binding<ActivityPostBinding>(R.layout.activity_post)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            lifecycleOwner = this@PostActivity
            viewModel = viewModelPost
        }

        initializeUI()
    }

    private fun initializeUI() {
        binding.recyclerViewPost.adapter = PostListAdapter(this)
        binding.recyclerViewPost.layoutManager = LinearLayoutManager(this)

        binding.swipeContainer.run {
            setOnRefreshListener { viewModelPost.postListLiveData }
            isRefreshing = false
        }
    }

    override fun onItemClick(post: Post) {
        PostDetailActivity.startActivity(this, post)
    }

    override fun onItemLongClick(post: Post): Boolean {
        val result = viewModelPost.removePost(post)

        val msg = if (result) {
            getString(R.string.post_removed_successful)
        } else {
            getString(R.string.post_removed_error)
        }

        Snackbar.make(binding.root, msg, LENGTH_SHORT).show()

        return result
    }
}