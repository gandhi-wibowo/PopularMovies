package com.dizcoding.popularmovies.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dizcoding.popularmovies.R
import com.dizcoding.popularmovies.movie.MovieFragment

class HomePagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.home_tab_1, R.string.home_tab_2)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment()
            1 -> instantiateFragment("com.dizcoding.favorite.VaforiteFragment")
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size

    private fun instantiateFragment(className: String) : Fragment {
        return try {
            Class.forName(className).newInstance() as Fragment
        } catch (e: Exception) {
            Fragment()
        }
    }
}