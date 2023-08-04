package com.example.mynemt.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mynemt.R
import com.example.mynemt.databinding.FragmentHomeBinding
import com.example.mynemt.timer.DataHelper
import java.util.Date
import java.util.Timer
import java.util.TimerTask

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var dataHelper: DataHelper
    private val timer = Timer()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        try {
            dataHelper = DataHelper(requireActivity().applicationContext)
        } catch (e: Exception) {
            Log.i("tuanku", "Exp: " + e.message)
        }

        try {

            Log.i("tuanku", "timerCounting: " + dataHelper.timerCounting())

            if (dataHelper.timerCounting()) {
                startTimer()
                Log.i("tuanku", "TimerCountingStart")
            } else {
                stopTimer()
                Log.i("tuanku", "TimerCountingStop")
                if (dataHelper.startTime() != null && dataHelper.stopTime() != null) {
                    val time = Date().time - calcRestartTime().time
                    Log.i("tuanku", "TimerCountingTime")
                    binding!!.textViewTimer.text = timeStringFromLong(time)
                }
            }
            timer.scheduleAtFixedRate(TimeTask(), 0, 500)


        } catch (e: Exception) {
            Log.i("tuanku", "TimerExp: " + e.message)
        }

        binding!!.buttonClock.setOnClickListener { startStopAction() }

        val textView: TextView = binding!!.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

//        var preferencesManager: MyPreferencesManager = MyPreferencesManager(requireContext())
//        preferencesManager.fetchColumn("UserClockIn")
//        Log.i("HomeTAG", "userClock: " + preferencesManager.fetchColumn("UserClockIn"))

//        if (preferencesManager.fetchColumn("UserClockIn").equals("yes")){
//            binding.buttonClock.text = getString(R.string.clock_out)
//        }else if(preferencesManager.fetchColumn("UserClockIn").equals("no")){
//            binding.buttonClock.text = getString(R.string.clock_in)
//        }else{
//            binding.buttonClock.text = getString(R.string.clock_in)
//        }

        return root
    }

    private inner class TimeTask : TimerTask() {
        override fun run() {
            if (dataHelper.timerCounting()) {
                try {
                    val time = Date().time - dataHelper.startTime()!!.time
                    Log.i("tuanku", "timeInTimeTask: $time")
                    binding!!.textViewTimer.text = timeStringFromLong(time)
                } catch (ex: Exception) {
                    Log.i("tuanku", "timeInTimeTaskExp: " + ex.message)
                }

            }
        }
    }

    private fun resetAction() {
        dataHelper.setStopTime(null)
        dataHelper.setStartTime(null)
        stopTimer()
        binding!!.textViewTimer.text = timeStringFromLong(0)
    }

    private fun stopTimer() {
        dataHelper.setTimerCounting(false)
        binding!!.buttonClock.text = getString(R.string.clock_in)
//        dataHelper.setStopTime(null)
//        dataHelper.setStartTime(null)
//        stopTimer()
//        binding.timeTV.text = timeStringFromLong(0)
    }

    private fun startTimer() {
        dataHelper.setTimerCounting(true)
        binding!!.buttonClock.text = getString(R.string.clock_out)
    }

    private fun startStopAction() {
        if (dataHelper.timerCounting()) {
            dataHelper.setStopTime(Date())
//            resetAction()
            stopTimer()
        } else {
            if (dataHelper.stopTime() != null) {
                dataHelper.setStartTime(calcRestartTime())
                dataHelper.setStopTime(null)
            } else {
                dataHelper.setStartTime(Date())
            }
            startTimer()
        }
    }

    private fun calcRestartTime(): Date {
        val diff = dataHelper.startTime()!!.time - dataHelper.stopTime()!!.time
        return Date(System.currentTimeMillis() + diff)
    }

    private fun timeStringFromLong(ms: Long): String {
        val seconds = (ms / 1000) % 60
        val minutes = (ms / (1000 * 60) % 60)
        val hours = (ms / (1000 * 60 * 60) % 24)

        Log.i("tuanku", "timeStringHours: $hours")
        Log.i("tuanku", "timeStringMinutes: $minutes")
        Log.i("tuanku", "timeStringSeconds: $seconds")

//        return  "$hours:$minutes:$seconds"

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hours: Long, minutes: Long, seconds: Long): String {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

