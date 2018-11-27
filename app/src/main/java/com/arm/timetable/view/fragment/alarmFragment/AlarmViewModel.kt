package com.arm.timetable.view.fragment.alarmFragment

import com.arm.timetable.base.BaseViewModel
import timber.log.Timber

class AlarmViewModel(val alarmView: AlarmView) : BaseViewModel() {
    private var alarmViewState : AlarmViewState = AlarmViewState()

    fun taskOne() {
        addTask {
            alarmView.clickOne().doOnEvent { t1, t2 ->
                alarmView.navigation(AlarmView.NavigationAlarm.ONE)
            }
        }
    }

    fun taskTwo() {
        addTask {
            alarmView.clickTwo().doOnEvent { t1, t2 ->
                alarmView.render(alarmViewState.copy()
                    .also { alarmViewState = it })
                Timber.e(alarmViewState.toString())
                alarmView.navigation(AlarmView.NavigationAlarm.TWO)
            }
        }
    }
}
