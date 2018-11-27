package com.arm.timetable.extension

import timber.log.Timber

fun Any.print() {
    Timber.e(this.toString())
}