package com.arm.timetable.view.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arm.timetable.R
import com.arm.timetable.view.activity.mainActivity.MainAdapter
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {
    lateinit var adapter : MainAdapter

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MainAdapter(childFragmentManager)
        viewPager.adapter = adapter
        tabLayout?.setOnClickTabListener { position ->
            viewPager.currentItem = position

        }
        tabLayout.setViewPager(viewPager,adapter)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
