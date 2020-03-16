package com.br.weightcontrol.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.weightcontrol.R
import com.br.weightcontrol.data.goal.Goal
import com.br.weightcontrol.data.weight.Weight
import com.br.weightcontrol.extension.formatToString
import com.br.weightcontrol.util.LayoutUtil
import kotlinx.android.synthetic.main.item_goal_horizontal.view.*
import kotlinx.android.synthetic.main.item_weight.view.*

class DashboardGoalAdapter(private val list: List<Goal>) :
    RecyclerView.Adapter<DashboardGoalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weight_horizontal, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val goal = list[position]
        holder.bindView(goal)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(goal: Goal) {
            with(itemView) {
                val start = goal.dateStart.formatToString().substring(0, 5)
                textViewDate.text = start
                val weight = "${goal.end} Kg"
                textViewWeight.text = weight.toString()
                textViewWeight.setTextColor(LayoutUtil.getColor(android.R.color.holo_green_dark))
            }
        }
    }
}