package com.arm.timetable

import android.app.Application
import com.arm.timetable.view.activity.mainActivity.presenterMainActivity
import com.arm.timetable.view.fragment.alarmFragment.alarmModule
import io.realm.Realm
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class RootApplication : Application() {
    override fun onCreate() {
                super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
                startKoin(this, arrayListOf(presenterMainActivity, alarmModule))
        getKoin().getOrCreateScope("main")
        Realm.init(this)
    }
}