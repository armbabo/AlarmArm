package com.arm.timetable.view.activity.mainActivity

import android.os.Bundle
import com.arm.timetable.R
import com.arm.timetable.base.BaseActivity
import com.arm.timetable.model.TestViewModel
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope
import org.koin.android.scope.ext.android.getScope
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity<MainActivityViewModel>(), MainActivityView {

    private val presenter: MainActivityViewModel = get(scope = getOrCreateScope("main")) { parametersOf(this) }
    val testViewModel: TestViewModel by inject()
    val publishSubject: PublishSubject<String> = PublishSubject.create()

    var adapter : MainAdapter ?= MainAdapter(supportFragmentManager)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindScope(getScope("main"))
        viewPager.adapter = adapter
        tabLayout?.setOnClickTabListener {position ->
            viewPager.currentItem = position

        }
        tabLayout.setViewPager(viewPager,adapter)
    }

    override fun createPresenter(): MainActivityViewModel {
        return presenter
    }
}
