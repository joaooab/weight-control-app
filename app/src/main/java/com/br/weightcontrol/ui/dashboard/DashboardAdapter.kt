package com.br.weightcontrol.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.weightcontrol.R
import com.br.weightcontrol.data.ItemDay
import kotlinx.android.synthetic.main.item_day.view.*

class DashboardAdapter(private val list: List<ItemDay>) :
    RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemDay = list[position]
        holder.bindView(itemDay)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(itemDay: ItemDay) {
            with(itemView) {
                textViewDay.text = itemDay.day.toString()
                textViewDayOfWeek.text = itemDay.dayOfWeek
            }
        }
    }
}