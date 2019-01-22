package com.arm.timetable.extension

import timber.log.Timber

fun Any.print(mess : String ?= "") {
    Timber.e("${this} $mess ")
}