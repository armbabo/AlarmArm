package com.arm.timetable.model

import android.arch.lifecycle.MutableLiveData

data class TestViewModel(var data : MutableLiveData<String>, var arm : Int)