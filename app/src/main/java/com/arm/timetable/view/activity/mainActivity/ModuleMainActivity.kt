package com.arm.timetable.view.activity.mainActivity

import org.koin.dsl.module.module

val presenterMainActivity = module {
    scope("main") {
        MainActivityViewModel(it[0])
    }
}