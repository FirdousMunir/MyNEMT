package com.example.mynemt.ui.current

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mynemt.R

class CurrentDayFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentDayFragment()
    }

    private lateinit var viewModel: CurrentDayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_day, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrentDayViewModel::class.java)
        // TODO: Use the ViewModel
    }

}