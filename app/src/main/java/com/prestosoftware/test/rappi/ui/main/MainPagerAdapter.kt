package com.prestosoftware.test.rappi.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.prestosoftware.test.rappi.ui.movie.upcoming.MovieUpcomingListFragment
import com.prestosoftware.test.rappi.ui.movie.popular.MoviePopularListFragment
import com.prestosoftware.test.rappi.ui.movie.topRated.MovieTopListFragment

class MainPagerAdapter(fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MoviePopularListFragment()
            1 -> MovieTopListFragment()
            else -> MovieUpcomingListFragment()
        }
    }

    override fun getCount() = 3
}
