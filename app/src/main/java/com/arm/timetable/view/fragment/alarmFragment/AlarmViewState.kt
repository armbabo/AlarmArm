package com.arm.timetable.view.fragment.alarmFragment

data class AlarmViewState(val alarm : Alarm ?= null)

data class Alarm(val longTime : Long ?= null, val isOn : Boolean ?= true)