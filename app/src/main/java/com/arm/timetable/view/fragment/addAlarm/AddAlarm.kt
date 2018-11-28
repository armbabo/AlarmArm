package com.arm.timetable.view.fragment.addAlarm

import android.os.Bundle
import android.view.View
import com.arm.timetable.R
import com.arm.timetable.base.BaseFragment
import com.arm.timetable.extension.print
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.item_alarm.*
import java.util.*


class AddAlarm : BaseFragment<AddAlarmViewModel,AddAlarmView>() {

    companion object {
        fun newInstance() = AddAlarm()
    }

    private lateinit var viewModel: AddAlarmViewModel

    override fun createViewModel(): AddAlarmViewModel {
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTime.setOnClickListener {
            val now = Calendar.getInstance()
            val dpd = TimePickerDialog.newInstance(
                { view, hourOfDay, minute, second ->
                    hourOfDay.print()
                    minute.print()
                    second.print()
                }
                , now.get(Calendar.YEAR)
                ,now.get(Calendar.MONTH)
                ,now.get(Calendar.DAY_OF_MONTH)
            ,true
            )
            dpd.show(childFragmentManager,"time")
        }
    }

    override fun layoutResource(): Int {
        return R.layout.add_alarm_fragment
    }
}
