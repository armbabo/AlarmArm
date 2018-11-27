package com.arm.timetable.view.activity.mainActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.arm.timetable.view.fragment.alarmFragment.AlarmFragment
import com.athbk.ultimatetablayout.IFTabAdapter

class MainAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm), IFTabAdapter {

    override fun isEnableBadge(p0: Int): Boolean {
        return true
    }

    override fun getIcon(p0: Int): Int {
        return 0
    }

    override fun getTitle(p0: Int): String {
        return "arm"
    }


    override fun getItem(p0: Int): Fragment {
        return AlarmFragment.newInstance()
    }

    override fun getCount(): Int {
        return 2
    }
}