package com.arm.timetable.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P : BaseViewModel> : AppCompatActivity() {

    abstract fun createPresenter() : P

    override fun onDestroy() {
//        createPresenter().removeAllTask()
        super.onDestroy()
    }
}