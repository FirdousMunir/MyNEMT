package com.example.mynemt.ui.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mynemt.R
import com.example.mynemt.databinding.FragmentCurrentAllTripsBinding
import com.example.mynemt.databinding.FragmentCurrentDayBinding


class CurrentAllTripsFragment : Fragment() {

    lateinit var binding: FragmentCurrentAllTripsBinding
    lateinit var recycledViewAllTrips : RecyclerView
    lateinit var textViewNoTrips : TextView
    lateinit var currentAllSwipe : SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_current_all_trips, container, false)

        binding = FragmentCurrentAllTripsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recycledViewAllTrips = binding.recyclerViewCurrentPending
        textViewNoTrips = binding.textViewCurrentPendingMessage
        currentAllSwipe = binding.pendingSwipeContainer


        return root

    }

    companion object {

        var TAG = "CurrentAllTripsFragmentTAG"

    }

}