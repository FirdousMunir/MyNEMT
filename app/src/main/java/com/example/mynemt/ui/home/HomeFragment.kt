package com.example.mynemt.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mynemt.R
import com.example.mynemt.databinding.FragmentHomeBinding
import com.example.mynemt.utils.MyPreferencesManager

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        var preferencesManager: MyPreferencesManager = MyPreferencesManager(requireContext())

        val buttonClock : Button = binding.buttonClockStart
        homeViewModel.initManager(preferencesManager)

        if (homeViewModel.icClockIn.equals("yes")){
            buttonClock.text = getString(R.string.clock_out)
        }else if(homeViewModel.icClockIn.equals("no")){
            buttonClock.text = getString(R.string.clock_in)
        }else{
            buttonClock.text = "Test"
        }


        buttonClock.setOnClickListener(View.OnClickListener {
//            yahn data display krwana h

        })


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}