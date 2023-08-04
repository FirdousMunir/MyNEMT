package com.example.mynemt.ui.current

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.mynemt.R

class CurrentDayTabsAdapter(fragmentManager: FragmentManager, mContext:Context) : FragmentStatePagerAdapter(fragmentManager) {

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tab_trips, R.string.tab_completed)
    private val mContext : Context = mContext

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                val allTripsFragment = CurrentAllTripsFragment()
                fragment = allTripsFragment
            }

            1 -> {
                val completedFragment = CurrentCompletedTripsFragment()
                fragment = completedFragment
            }
        }
        return fragment!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }

}