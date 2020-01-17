package com.br.weightcontrol.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.weightcontrol.R
import com.br.weightcontrol.data.Weight
import com.br.weightcontrol.extension.formatToString
import kotlinx.android.synthetic.main.item_weight.view.*

class DashboardAdapter(private val list: List<Weight>) :
    RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weight, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weight = list[position]
        holder.bindView(weight)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(weight: Weight) {
            with(itemView) {
                val weightString = "${weight.weight} Kg"
                textViewWeight.text = weightString
                textViewDate.text = weight.date.formatToString()
            }
        }
    }
}