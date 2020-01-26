package com.br.weightcontrol.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.br.weightcontrol.R
import com.br.weightcontrol.data.goal.GoalWithWeight
import com.br.weightcontrol.extension.supportFragmentManager
import com.br.weightcontrol.ui.component.NumberPickerDialog
import com.br.weightcontrol.util.LayoutUtil
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.card_view_goal.*
import kotlinx.android.synthetic.main.card_view_weight.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeWeight()
        observeGoal()
        observeError()
        setUpAddWeight()
        setUpAddGoal()
    }

    private fun observeError() {
        viewModel.onError.observe(this, Observer {
            showSnackBarError(it)
        })
    }

    private fun showSnackBarError(message: String) {
        Snackbar.make(constraintLayout, message, Snackbar.LENGTH_LONG).show()
    }

    private fun setUpAddGoal() {
        textViewCreateGoal.setOnClickListener {
            val currentWeight = viewModel.weight.value
            if (currentWeight == null) {
                showSnackBarError(LayoutUtil.getString(R.string.error_goal_weight_null))
            } else {
                supportFragmentManager {
                    val title = LayoutUtil.getString(R.string.text_what_is_your_goal)
                    NumberPickerDialog.newInstance(title, currentWeight) {
                        viewModel.addGoal(currentWeight, it)
                    }.show(this, "")
                }
            }
        }
    }

    private fun observeGoal() {
        viewModel.goal.observe(this, Observer {
            if (it != null) {
                setUpGoal(it)
                constraintLayoutGoal.visibility = View.VISIBLE
                textViewCreateGoal.visibility = View.GONE
            }
        })
    }

    private fun setUpGoal(goalWithWeight: GoalWithWeight) {
        val goal = goalWithWeight.goal
        val weight = goalWithWeight.weight
        val total = goal.begin - goal.end
        val currentTotal = weight.weight - goal.end
        val list = createPieEntrys(total, currentTotal)
        val pieDataSet = PieDataSet(list, "teste").apply {
            val colors = mutableListOf(
                LayoutUtil.getColor(R.color.colorPrimary),
                LayoutUtil.getColor(R.color.colorAccent)
            )
            setColors(colors)
            valueTextSize = 18f
            valueTextColor = LayoutUtil.getColor(R.color.colorGrayLight)
        }
        pieChart.apply {
            data = PieData(pieDataSet)
            setCenterTextOffset(0f, -20f)
            legend.isEnabled = false
            centerText = "95%"
            setCenterTextSize(36F)
            description.isEnabled = false
        }
    }

    private fun createPieEntrys(
        total: Double,
        currentTotal: Double
    ): MutableList<PieEntry> {
        return if (currentTotal >= total) {
            mutableListOf(
                PieEntry(currentTotal.toFloat())
            )
        } else {
            mutableListOf(
                PieEntry((total - currentTotal).toFloat()),
                PieEntry(currentTotal.toFloat())
            )
        }
    }

    private fun observeWeight() {
        viewModel.weight.observe(this, Observer {
            if (it != null) {
                textViewWeight.text = it.weight.toString()
                linearLayoutWeight.visibility = View.VISIBLE
                textViewAddWeight.visibility = View.GONE
            }
        })
    }

    private fun setUpAddWeight() {
        textViewAddWeight.setOnClickListener {
            val lastWeight = viewModel.getLast()
            supportFragmentManager {
                val title = LayoutUtil.getString(R.string.text_what_is_your_weight)
                NumberPickerDialog.newInstance(title, lastWeight) {
                    viewModel.addWeight(it)
                }.show(this, "")
            }
        }
    }
}