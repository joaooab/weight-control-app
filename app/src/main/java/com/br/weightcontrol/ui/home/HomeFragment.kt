package com.br.weightcontrol.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.br.weightcontrol.R
import com.br.weightcontrol.extension.supportFragmentManager
import com.br.weightcontrol.ui.component.NumberPickerDialog
import com.br.weightcontrol.util.LayoutUtil
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.main.card_view_goal.*
import kotlinx.android.synthetic.main.card_view_weight.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAddWeight()
        observeWeight()
        val list = mutableListOf(
            PieEntry(80F),
            PieEntry(5F)
        )
        val pieDataSet = PieDataSet(list, "teste").apply {
            val colors = mutableListOf(
                LayoutUtil.getColor(R.color.colorPrimary),
                LayoutUtil.getColor(R.color.colorAccent)
            )
            setColors(colors)
            valueTextSize = 18f
            valueTextColor = LayoutUtil.getColor(R.color.gray_light)
        }
        pieChart.apply {
            data = PieData(pieDataSet)
            setCenterTextOffset(0f, -20f)
            legend.isEnabled = false
            centerText = "95%"
            setCenterTextSize(36F)
        }
    }

    private fun observeWeight() {
        viewModel.weight.observe(this, Observer {
            textViewWeight.text = it.weight.toString()
            linearLayoutWeight.visibility = View.VISIBLE
            textViewAddWeight.visibility = View.GONE
        })
    }

    private fun setUpAddWeight() {
        textViewAddWeight.setOnClickListener {
            supportFragmentManager {
                NumberPickerDialog.newInstance({
                    viewModel.addWeight(it)
                }).show(this, "")
            }
        }
    }
}