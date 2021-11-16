package com.prestosoftware.test.rappi.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prestosoftware.test.rappi.models.Resource
import com.prestosoftware.test.rappi.models.Video
import com.prestosoftware.test.rappi.models.entity.Movie
import com.prestosoftware.test.rappi.ui.movie.upcoming.MovieUpcomingListAdapter
import com.prestosoftware.test.rappi.ui.movie.VideoListAdapter
import com.prestosoftware.test.rappi.ui.movie.popular.MoviePopularListAdapter
import com.prestosoftware.test.rappi.ui.movie.topRated.MovieTopListAdapter
import com.prestosoftware.test.rappi.util.extension.bindResource
import com.prestosoftware.test.rappi.util.extension.visible

@BindingAdapter("adapterMoviePopularList")
fun bindAdapterMoviePopularList(view: RecyclerView, resource: Resource<List<Movie>>?) {
  view.bindResource(resource) {
    if (resource != null) {
      val adapter = view.adapter as? MoviePopularListAdapter
      adapter?.addMovieList(resource)
    }
  }
}

@BindingAdapter("adapterMovieTopList")
fun bindAdapterMovieTopList(view: RecyclerView, resource: Resource<List<Movie>>?) {
    view.bindResource(resource) {
        if (resource != null) {
            val adapter = view.adapter as? MovieTopListAdapter
            adapter?.addMovieList(resource)
        }
    }
}

@BindingAdapter("adapterMovieList")
fun bindAdapterMovieUpcomingList(view: RecyclerView, resource: Resource<List<Movie>>?) {
    view.bindResource(resource) {
        if (resource != null) {
            val adapter = view.adapter as? MovieUpcomingListAdapter
            adapter?.addMovieList(resource)
        }
    }
}

@BindingAdapter("adapterVideoList")
fun bindAdapterVideoList(view: RecyclerView, resource: Resource<List<Video>>?) {
  view.bindResource(resource) {
    if (resource != null) {
      val adapter = view.adapter as? VideoListAdapter
      adapter?.addVideoList(resource)
      if (resource.data?.isNotEmpty()!!) {
        view.visible()
      }
    }
  }
}
