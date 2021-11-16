package com.prestosoftware.test.rappi.ui.movie.popular

import android.view.View
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.prestosoftware.test.rappi.data.api.Api
import com.prestosoftware.test.rappi.models.entity.Movie
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_movie_popular.view.*

class MoviePopularListViewHolder(
    val view: View,
    private val delegate: Delegate
) : BaseViewHolder(view) {

    interface Delegate {
        fun onItemClick(movie: Movie)
    }

    private lateinit var movie: Movie

    @Throws(Exception::class)
    override fun bindData(data: Any) {
        if (data is Movie) {
            movie = data
            drawItem()
        }
    }

    private fun drawItem() {
        itemView.run {
            item_mp_title.text = movie.title
            movie.poster_path?.let {
                Glide.with(context)
                    .load(Api.getPosterPath(it))
                    .listener(GlidePalette.with(Api.getPosterPath(it))
                        .use(BitmapPalette.Profile.VIBRANT)
                        .intoBackground(item_mp_palette)
                        .crossfade(true))
                    .into(item_mp_post)
            }
        }
    }

    override fun onClick(v: View?) {
        delegate.onItemClick(movie)
    }

    override fun onLongClick(v: View?) = false
}
