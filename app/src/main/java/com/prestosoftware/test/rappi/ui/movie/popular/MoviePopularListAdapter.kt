package com.prestosoftware.test.rappi.ui.movie.popular

import android.view.View
import com.prestosoftware.test.R
import com.prestosoftware.test.rappi.models.Resource
import com.prestosoftware.test.rappi.models.entity.Movie
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow

class MoviePopularListAdapter(
    private val delegate: MoviePopularListViewHolder.Delegate
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
        return R.layout.item_movie_popular
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return MoviePopularListViewHolder(view, delegate)
    }
}
