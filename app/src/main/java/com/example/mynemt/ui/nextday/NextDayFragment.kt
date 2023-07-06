package com.example.mynemt.ui.nextday

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mynemt.R

class NextDayFragment : Fragment() {

    companion object {
        fun newInstance() = NextDayFragment()
    }

    private lateinit var viewModel: NextDayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_next_day, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NextDayViewModel::class.java)
        // TODO: Use the ViewModel
    }

}