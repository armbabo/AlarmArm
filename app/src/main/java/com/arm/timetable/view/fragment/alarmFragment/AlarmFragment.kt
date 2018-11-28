package com.arm.timetable.view.fragment.alarmFragment

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arm.timetable.R
import com.arm.timetable.base.BaseFragment
import com.arm.timetable.view.fragment.addAlarm.AddAlarm
import io.reactivex.Maybe
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.alarm_fragment.*
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class AlarmFragment : BaseFragment<AlarmViewModel,AlarmView>(), AlarmView {

    private val clickOne : PublishSubject<String> = PublishSubject.create()
    private val clickTwo : PublishSubject<Int> = PublishSubject.create()

    private val viewModel: AlarmViewModel = get(parameters = { parametersOf(this) })
    private val alarmAdapter = AlarmAdapter()
    companion object {
        fun newInstance() = AlarmFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alarmAdapter.setListItem(mutableListOf(Alarm(1),Alarm(1),Alarm(1)))
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity).apply {
                orientation = LinearLayoutManager.VERTICAL
                reverseLayout = false
            }
            addItemDecoration(DividerItemDecoration(activity,DividerItemDecoration.VERTICAL))
            adapter = alarmAdapter
        }
        btnAddAlarm.setOnClickListener {
            goToFragment(AddAlarm.newInstance())
        }
    }

    override fun render(alarmViewState: AlarmViewState) {
        Timber.e(alarmViewState.toString())
    }

    override fun navigation(navigationAlarm: AlarmView.NavigationAlarm) {
        when(navigationAlarm) {
            AlarmView.NavigationAlarm.ONE -> {
                Timber.e("1")
            }
            else -> {
                Timber.e("2")
            }
        }
    }

    override fun clickOne(): Maybe<String> {
        return clickOne.firstElement()
    }

    override fun clickTwo(): Maybe<Int> {
        return clickTwo.firstElement()
    }

    override fun createViewModel(): AlarmViewModel {
        return viewModel
    }

    override fun layoutResource(): Int {
       return R.layout.alarm_fragment
    }
}
