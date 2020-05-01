package com.br.weightcontrol.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.br.weightcontrol.R
import com.br.weightcontrol.data.DashboardWeight
import com.br.weightcontrol.data.weight.Weight
import com.br.weightcontrol.extension.formatToString
import com.br.weightcontrol.util.ChartDataValueFormatter
import com.br.weightcontrol.util.LayoutUtil
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.card_view_history.*
import kotlinx.android.synthetic.main.card_view_history_goal_list.*
import kotlinx.android.synthetic.main.card_view_history_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLoading()
        observeWeights()
        observeGoals()
        observeDashboardWeight()
    }

    private fun observeGoals() {
        dashboardViewModel.goal.observe(viewLifecycleOwner, Observer {
            recyclerViewGoal.adapter = DashboardGoalAdapter(it)
        })
    }

    private fun observeLoading() {
        dashboardViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                progressBarHistory.visibility = View.VISIBLE
                lineChart.visibility = View.INVISIBLE
            } else {
                progressBarHistory.visibility = View.GONE
                lineChart.visibility = View.VISIBLE
            }
        })
    }

    private fun observeDashboardWeight() {
        dashboardViewModel.dashboardWeight.observe(viewLifecycleOwner, Observer {
            setUpChart(it)
        })
    }

    private fun observeWeights() {
        dashboardViewModel.weights.observe(viewLifecycleOwner, Observer {
            setUpHistoryList(it)
        })
    }

    private fun setUpChart(dashboard: DashboardWeight) {
        val (dates, lineDataSet) = createDataSet(dashboard.weights)
        lineChart.apply {
            xAxis.apply {
                valueFormatter = ChartDataValueFormatter(dates)
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                isGranularityEnabled = true
            }
            axisLeft.apply {
                setDrawGridLines(false)
                val min = dashboard.minWeight?.weight ?: 100.0
                val max = dashboard.maxWeight?.weight ?: 50.0
                axisMinimum = min.toFloat() - 5f
                axisMaximum = max.toFloat() + 5f
            }
            axisRight.isEnabled = false
            description.isEnabled = false
            legend.isEnabled = false
            animateY(1000)
            data = LineData(lineDataSet)
        }
    }

    private fun createDataSet(weights: List<Weight>): Pair<MutableList<String>, LineDataSet> {
        val entrys = mutableListOf<Entry>()
        val dates = mutableListOf<String>()
        weights.forEachIndexed { index, weight ->
            val entry = Entry(index.toFloat(), weight.weight.toFloat())
            entrys.add(entry)
            dates.add(weight.date.formatToString())
        }
        val lineDataSet = LineDataSet(entrys, "Peso").apply {
            lineWidth = 1.8f
            color = LayoutUtil.getColor(R.color.colorPrimaryDark)
            fillColor = LayoutUtil.getColor(R.color.colorDashboard)
            circleColors = mutableListOf(LayoutUtil.getColor(R.color.colorPrimaryDark))
            fillAlpha = 170
            setDrawFilled(true)
            setDrawCircles(true)
        }
        return Pair(dates, lineDataSet)
    }

    private fun setUpHistoryList(list: List<Weight>) {
        recyclerView.adapter = DashboardAdapter(list)
    }
}