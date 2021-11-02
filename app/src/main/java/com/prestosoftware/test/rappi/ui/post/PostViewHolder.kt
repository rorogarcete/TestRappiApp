package com.prestosoftware.test.rappi.ui.post

import android.view.View
import com.prestosoftware.test.rappi.models.entity.Post
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_post.view.item_post_author
import kotlinx.android.synthetic.main.item_post.view.item_post_title

class PostViewHolder(
    val view: View,
    private val delegate: Delegate
) : BaseViewHolder(view) {

    interface Delegate {
        fun onItemClick(post: Post)
    }

    private lateinit var post: Post

    @Throws(Exception::class)
    override fun bindData(data: Any) {
        if (data is Post) {
            post = data
            drawItem()
        }
    }

    private fun drawItem() {
        itemView.run {
            item_post_title.text = post.title
            item_post_author.text = "${post.author.orEmpty()} - ${post.createdAt.orEmpty()}"
        }
    }

    override fun onClick(v: View?) {
        delegate.onItemClick(post)
    }

    override fun onLongClick(v: View?) = false
}
