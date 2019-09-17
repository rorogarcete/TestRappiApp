package com.prestosoftware.test.rappi.ui.movie.upcoming

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.prestosoftware.test.rappi.R
import com.prestosoftware.test.rappi.databinding.MainFragmentMovieBinding
import com.prestosoftware.test.rappi.models.Status
import com.prestosoftware.test.rappi.models.entity.Movie
import com.prestosoftware.test.rappi.ui.main.MainActivityViewModel
import com.prestosoftware.test.rappi.ui.movie.detail.MovieDetailActivity
import com.prestosoftware.test.rappi.util.compose.ViewModelFragment
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import kotlinx.android.synthetic.main.main_fragment_movie.*

@Suppress("SpellCheckingInspection")
class MovieUpcomingListFragment : ViewModelFragment(), MovieUpcomingListViewHolder.Delegate {

  private val viewModel by viewModel<MainActivityViewModel>()
  private lateinit var binding: MainFragmentMovieBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = binding(inflater, R.layout.main_fragment_movie, container)
    binding.viewModel = viewModel
    binding.lifecycleOwner = this
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initializeUI()
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    loadMore(page = 1)
  }

  private fun initializeUI() {
    recyclerView.adapter = MovieUpcomingListAdapter(this)
    recyclerView.layoutManager = GridLayoutManager(context, 2)
    val paginator = RecyclerViewPaginator(
      recyclerView = recyclerView,
      isLoading = { viewModel.getMovieListValues()?.status == Status.LOADING },
      loadMore = { loadMore(it) },
      onLast = { viewModel.getMovieListValues()?.onLastPage!! }
    )
    paginator.currentPage = 1
  }

  private fun loadMore(page: Int) {
    viewModel.postMoviePage(page)
  }

  override fun onItemClick(movie: Movie) {
    MovieDetailActivity.startActivityModel(context, movie)
  }
}
