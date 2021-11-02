package com.prestosoftware.test.rappi.ui.post

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.prestosoftware.test.rappi.R
import com.prestosoftware.test.rappi.databinding.ActivityPostBinding
import com.prestosoftware.test.rappi.models.entity.Post
import com.prestosoftware.test.rappi.util.compose.ViewModelActivity
import kotlinx.android.synthetic.main.activity_post.recycler_view_post

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
        recycler_view_post.adapter = PostListAdapter(this)
        recycler_view_post.layoutManager = LinearLayoutManager(this)
    }

    override fun onItemClick(post: Post) {
        TODO("Not yet implemented")
    }
}