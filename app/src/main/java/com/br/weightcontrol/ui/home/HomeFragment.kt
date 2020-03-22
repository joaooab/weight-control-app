package com.br.weightcontrol.ui.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.br.weightcontrol.R
import com.br.weightcontrol.data.goal.Goal
import com.br.weightcontrol.data.user.Session
import com.br.weightcontrol.data.weight.Weight
import com.br.weightcontrol.extension.supportFragmentManager
import com.br.weightcontrol.ui.component.NumberPickerDialog
import com.br.weightcontrol.util.LayoutUtil
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.card_view_goal.*
import kotlinx.android.synthetic.main.card_view_imc.*
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
        observeUser()
        observeWeight()
        observeGoal()
        observeError()
        setUpUser()
        setUpAddWeight()
        setUpAddGoal()
        setupMoreWeight()
        setupMoreGoal()
    }

    private fun setUpUser() {
        if (Session.user.value == null) {
            navigateUserFragment()
        }
    }

    private fun observeUser() {
        Session.user.observe(viewLifecycleOwner, Observer {
            textViewUserName.text = it.name
        })
    }

    private fun navigateUserFragment() {
        val intent = HomeFragmentDirections.actionNavigationHomeToUserFragment()
        Navigation.findNavController(view!!).navigate(intent)
    }

    private fun setupMoreWeight() {
        imageViewMoreWeight.setOnClickListener {
            showPopup(R.menu.menu_weight, it) { menu ->
                when (menu.itemId) {
                    R.id.menu_weight_edit -> {
                        updateWeight()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun setupMoreGoal() {
        imageViewMoreGoal.setOnClickListener {
            showPopup(R.menu.menu_goal, it) { menu ->
                when (menu.itemId) {
                    R.id.menu_goal_edit -> {
                        updateGoal()
                        true
                    }
                    R.id.menu_goal_delete -> {
                        viewModel.deleteGoal()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun observeError() {
        viewModel.onError.observe(viewLifecycleOwner, Observer {
            showSnackBarError(it)
        })
    }

    private fun showSnackBarError(message: String) {
        Snackbar.make(constraintLayout, message, Snackbar.LENGTH_LONG).show()
    }

    private fun setUpAddGoal() {
        textViewCreateGoal.setOnClickListener {
            addGoal()
        }
    }

    private fun addGoal() {
        openDialogGoal { viewModel.addGoal(it) }
    }

    private fun updateGoal() {
        openDialogGoal { viewModel.updateGoal(it) }
    }

    private fun openDialogGoal(onFinish: (Goal) -> Unit) {
        val currentWeight = viewModel.weight.value
        if (currentWeight == null) {
            showSnackBarError(LayoutUtil.getString(R.string.error_goal_weight_null))
        } else {
            supportFragmentManager {
                val title = LayoutUtil.getString(R.string.text_what_is_your_goal)
                NumberPickerDialog.newInstance(title, currentWeight) {
                    val goal = Goal(
                        begin = currentWeight.weight,
                        current = currentWeight.weight,
                        end = it.weight
                    )
                    onFinish(goal)
                }.show(this, "")
            }
        }
    }

    private fun observeGoal() {
        viewModel.goal.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                constraintLayoutGoal.visibility = View.VISIBLE
                constraintFinishGoal.visibility = View.GONE
                textViewCreateGoal.visibility = View.GONE
                imageViewMoreGoal.visibility = View.VISIBLE
                val begin = "${it.begin} Kg"
                textViewBegin.text = begin
                val goal = "${it.end} Kg"
                textViewGoal.text = goal
                val current = "${it.current} Kg"
                textViewCurrent.text = current
                setUpPercentGoal(it)
            } else {
                constraintLayoutGoal.visibility = View.GONE
                textViewCreateGoal.visibility = View.VISIBLE
                imageViewMoreGoal.visibility = View.GONE
            }
        })
    }

    fun setupFinishGoal() {
        constraintFinishGoal.visibility = View.VISIBLE
        constraintLayoutGoal.visibility = View.GONE
        textViewFinishGoal.setOnClickListener {
            viewModel.finishGoal()
            addGoal()
        }
    }

    private fun setUpPercentGoal(goal: Goal) {
        val percent = calculatePercent(goal.begin, goal.current, goal.end)
        val animator = ValueAnimator.ofInt(0, percent.toInt()).apply {
            duration = 2000
            addUpdateListener {
                if (textViewPercent != null) {
                    textViewPercent.text = it.animatedValue.toString()
                }
            }
        }
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                if (percent >= 100) {
                    setupFinishGoal()
                }
            }
        })
        animator.start()
    }

    private fun calculatePercent(begin: Double, current: Double, end: Double): Double {
        return if (begin > current) {
            val totalGoal = begin - end
            val totalCurrent = begin - current
            totalCurrent / totalGoal * 100
        } else {
            0.0
        }
    }

    private fun observeWeight() {
        viewModel.weight.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                textViewStartGoal.text = it.weight.toString()
                linearLayoutWeight.visibility = View.VISIBLE
                textViewAddWeight.visibility = View.GONE
                imageViewMoreWeight.visibility = View.VISIBLE
            } else {
                linearLayoutWeight.visibility = View.GONE
                textViewAddWeight.visibility = View.VISIBLE
                imageViewMoreWeight.visibility = View.GONE
            }
//            calculateIMC(it)
        })
    }

    private fun calculateIMC(weight: Weight) {
        textViewIMC.text = viewModel.calculateIMC(weight).toString()
    }

    private fun setUpAddWeight() {
        textViewAddWeight.setOnClickListener {
            addWeight()
        }
    }

    private fun addWeight() {
        openDialogWeight { viewModel.addWeight(it) }
    }

    private fun updateWeight() {
        openDialogWeight { viewModel.updateWeight(it) }
    }

    fun openDialogWeight(onFinish: (Weight) -> Unit) {
        val lastWeight = viewModel.getLastWeight()
        supportFragmentManager {
            val title = LayoutUtil.getString(R.string.text_what_is_your_weight)
            NumberPickerDialog.newInstance(title, lastWeight) {
                onFinish(it)
            }.show(this, "")
        }
    }

    fun showPopup(menu: Int, v: View, onClickPopup: (MenuItem) -> Boolean) {
        PopupMenu(context!!, v).apply {
            setOnMenuItemClickListener {
                onClickPopup(it)
            }
            inflate(menu)
            show()
        }
    }

}