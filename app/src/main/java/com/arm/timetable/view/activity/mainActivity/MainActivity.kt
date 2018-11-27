package com.arm.timetable.view.activity.mainActivity

import android.os.Bundle
import com.arm.timetable.R
import com.arm.timetable.base.BaseActivity
import com.arm.timetable.model.TestViewModel
import com.arm.timetable.view.fragment.HomeFragment
import io.reactivex.subjects.PublishSubject
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindScope(getScope("main"))
        supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fl_add_fragment, HomeFragment.newInstance())
            ?.commit()
    }

    override fun createPresenter(): MainActivityViewModel {
        return presenter
    }
}
