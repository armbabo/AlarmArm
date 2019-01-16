package com.arm.timetable.view.fragment.alarmFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arm.timetable.R
import kotlinx.android.synthetic.main.item_alarm.view.*

class AlarmAdapter : RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {

    private var listItem : MutableList<Alarm> ?= arrayListOf()

    fun setListItem(listItem : MutableList<Alarm>) {
        this.listItem = listItem
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Alarm? {
        return listItem?.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): AlarmViewHolder {
        return AlarmViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.item_alarm,parent,false))
    }

    override fun getItemCount(): Int {
        return listItem?.size!!
    }

    override fun onBindViewHolder(viewHolder : AlarmViewHolder, position: Int) {
        viewHolder.tvTime.text = getItem(position)?.longTime.toString()
    }

    inner class AlarmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTime = itemView.tvTime
    }
}