package com.arm.timetable.base

import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<P : BaseViewModel> : AppCompatActivity() {

    abstract fun createPresenter() : P

    override fun onDestroy() {
//        createPresenter().removeAllTask()
        super.onDestroy()
    }
}