package com.arm.timetable.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arm.timetable.R

abstract class BaseFragment<VM : BaseViewModel,V> : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResource(),container,false)
    }

    fun goToFragment(fragment: androidx.fragment.app.Fragment, addToBackStack : Boolean ?= true, tag: String ?= null) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fl_add_fragment, fragment)
            ?.apply { if (addToBackStack!!) addToBackStack(tag)}
            ?.commit()
    }

    abstract fun createViewModel() : VM

    abstract fun layoutResource() : Int
}