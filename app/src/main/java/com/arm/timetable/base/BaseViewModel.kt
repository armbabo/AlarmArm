package com.arm.timetable.base

import androidx.lifecycle.ViewModel
import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel(){
    val compositeDisposable : CompositeDisposable = CompositeDisposable()

    fun addTask(observable: () -> Maybe<*>) {
        compositeDisposable.add(observable().subscribe())
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}