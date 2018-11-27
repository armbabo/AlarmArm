package com.arm.timetable.view.fragment.alarmFragment

import io.reactivex.Maybe

interface AlarmView {
    fun clickOne() : Maybe<String>
    fun clickTwo() : Maybe<Int>
    fun render(alarmViewState: AlarmViewState)
    fun navigation(navigationAlarm: NavigationAlarm)

    enum class NavigationAlarm {
        ONE,TWO
    }
}