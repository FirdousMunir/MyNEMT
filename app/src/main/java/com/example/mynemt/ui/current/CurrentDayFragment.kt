package com.example.mynemt.ui.current

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.mynemt.databinding.FragmentCurrentDayBinding
import com.google.android.material.tabs.TabLayout

class CurrentDayFragment : Fragment() {

    private lateinit var binding: FragmentCurrentDayBinding

    var viewPager: ViewPager? = null
    var tabs: TabLayout? = null
    var tabsAdapter: CurrentDayTabsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCurrentDayBinding.inflate(inflater, container, false)
        val root: View = binding.root

        try {
//            viewPager = binding.viewPagerCurrent
//            tabs = binding.tabsCurrent
//            tabsAdapter = CurrentDayTabsAdapter(childFragmentManager, requireContext())
//            viewPager!!.adapter
//            tabs!!.setupWithViewPager(viewPager)
//            tabsAdapter!!.notifyDataSetChanged()


            tabsAdapter = CurrentDayTabsAdapter(childFragmentManager, requireContext())
            viewPager = binding.viewPagerCurrent
            viewPager!!.adapter = tabsAdapter
            tabs = binding.tabsCurrent
            tabs!!.setupWithViewPager(viewPager)
            tabsAdapter!!.notifyDataSetChanged()


        } catch (e: Exception) {
            Log.i(TAG, "pagerExp: ${e.message}")
        }
        return root
    }

    companion object {
        fun newInstance() = CurrentDayFragment()
        val TAG: String = "CurrentDayFragmentTAG"
    }


}