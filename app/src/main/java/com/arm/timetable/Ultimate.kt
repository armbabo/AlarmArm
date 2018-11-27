package com.arm.timetable

import java.util.*

object Ultimate {
    /**
     * type : Calendar.YEAR,.....
     */
    fun getDetailTimeFromMillisecond(millisecond : Long ?= null, type : Int) : Int{
       return Calendar.getInstance().apply {
            if (millisecond != null) {
                timeInMillis = millisecond
            }
        }[type]
    }
}