package com.arm.timetable.view.fragment.addAlarm

import com.arm.timetable.R
import com.arm.timetable.base.BaseFragment

class AddAlarm : BaseFragment<AddAlarmViewModel,AddAlarmView>() {

    companion object {
        fun newInstance() = AddAlarm()
    }

    private lateinit var viewModel: AddAlarmViewModel

    override fun createViewModel(): AddAlarmViewModel {
        return viewModel
    }

    override fun layoutResource(): Int {
        return R.layout.add_alarm_fragment
    }
}
