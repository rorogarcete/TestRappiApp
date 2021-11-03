package com.prestosoftware.test.reign.ui.post

import android.view.View
import com.prestosoftware.test.reign.models.Post
import com.prestosoftware.test.rappi.util.formattedTimeLeft
import com.prestosoftware.test.rappi.util.getTimeInSeconds
import com.prestosoftware.test.rappi.util.secondsBetweenTwoDates
import com.prestosoftware.test.rappi.util.shouldShowCountdown
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_post.view.item_post_author
import kotlinx.android.synthetic.main.item_post.view.item_post_title
import java.util.Date
import kotlin.math.abs

class PostViewHolder(
    val view: View,
    private val delegate: Delegate
) : BaseViewHolder(view) {

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

            val secondsBetween = getTimeInSeconds(post.createdAt)
            val showCountdown24hs = shouldShowCountdown(secondsBetween)

            val timer = if (showCountdown24hs) {
                abs(
                    secondsBetweenTwoDates(
                        post.createdAt,
                        Date().toString()
                    )
                ).formattedTimeLeft().toString()
            } else {
                abs(
                    secondsBetweenTwoDates(
                        post.createdAt,
                        Date().toString()
                    )
                ).formattedTimeLeft(true).toString()
            }

            item_post_author.text = "${ post.author} - $timer"
        }
    }

    override fun onClick(v: View?) = delegate.onItemClick(post)

    override fun onLongClick(v: View?) = delegate.onItemLongClick(post)

    interface Delegate {
        fun onItemClick(post: Post)
        fun onItemLongClick(post: Post): Boolean
    }
}
