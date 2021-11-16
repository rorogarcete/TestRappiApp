package com.prestosoftware.test.rappi.ui.movie.upcoming

import android.view.View
import com.prestosoftware.test.R
import com.prestosoftware.test.rappi.models.Resource
import com.prestosoftware.test.rappi.models.entity.Movie
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow

class MovieUpcomingListAdapter(
    private val delegate: MovieUpcomingListViewHolder.Delegate
) : BaseAdapter() {

    init {
        addSection(ArrayList<Movie>())
    }

    fun addMovieList(resource: Resource<List<Movie>>) {
        resource.data?.let {
            sections()[0].addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow): Int {
        return R.layout.item_poster
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return MovieUpcomingListViewHolder(view, delegate)
    }
}
