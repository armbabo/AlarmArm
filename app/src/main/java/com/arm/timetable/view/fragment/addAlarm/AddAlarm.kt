package com.arm.timetable.view.fragment.addAlarm

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arm.timetable.R

class AddAlarm : Fragment() {

    companion object {
        fun newInstance() = AddAlarm()
    }

    private lateinit var viewModel: AddAlarmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_alarm_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddAlarmViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
