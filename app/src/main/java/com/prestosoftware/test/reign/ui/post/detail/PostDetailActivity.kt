package com.prestosoftware.test.reign.ui.post.detail

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.prestosoftware.test.rappi.R
import android.os.Bundle
import com.prestosoftware.test.reign.models.Post
import kotlinx.android.synthetic.main.activity_post_detail.*
import org.jetbrains.anko.startActivity

class PostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        web_view.loadUrl(getPostFromIntent().url.orEmpty())
    }

    private fun getPostFromIntent() =
        intent.getParcelableExtra(POST) as Post

    companion object {
        private const val POST = "POST"

        fun startActivity(context: Context?, post: Post) {
            context?.startActivity<PostDetailActivity>(POST to post)
        }
    }
}