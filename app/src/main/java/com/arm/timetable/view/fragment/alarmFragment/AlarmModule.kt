package com.arm.timetable.view.fragment.alarmFragment

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val alarmModule = module {
    viewModel { AlarmViewModel(it[0]) }
}