package com.prestosoftware.test.rappi.ui.post

import android.view.View
import com.prestosoftware.test.rappi.R
import com.prestosoftware.test.rappi.models.Resource
import com.prestosoftware.test.rappi.models.entity.Post
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow

class PostListAdapter(
    private val delegate: PostViewHolder.Delegate
) : BaseAdapter() {

    init {
        addSection(ArrayList<Post>())
    }

    fun addPostList(resource: Resource<List<Post>>) {
        resource.data?.let {
            sections()[0].addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow): Int {
        return R.layout.item_post
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return PostViewHolder(view, delegate)
    }
}
