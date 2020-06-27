package com.br.weightcontrol.ui.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.br.weightcontrol.R
import com.br.weightcontrol.data.user.User
import com.br.weightcontrol.data.weight.Weight
import com.br.weightcontrol.extension.decimalFormat
import com.br.weightcontrol.extension.showSnackBar
import com.br.weightcontrol.util.IMCUtil
import com.br.weightcontrol.util.LayoutUtil
import kotlinx.android.synthetic.main.card_view_perfil.*
import kotlinx.android.synthetic.main.card_view_progress.*
import org.koin.android.ext.android.inject

class PerfilFragment : Fragment() {

    private val viewModel: PerfilViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpButtonMore()
        observeUser()
        observeError()
        observeHigerWeight()
        observeLowerWeight()
        observeLastWeight()
        observeFirstWeight()
        observeTotalGoals()
    }

    private fun setUpWeight(
        textView: TextView,
        textViewImc: TextView,
        weight: Weight?
    ) {
        weight?.let {
            textView.text = weight.weight.toString()
            textViewImc.text = IMCUtil.calculate(weight).decimalFormat()
        }
    }

    private fun observeTotalGoals() {
        viewModel.totalGoals.observe(viewLifecycleOwner, Observer {
            textViewTotalGoals.text = it.toString()
        })
    }

    private fun observeFirstWeight() {
        viewModel.firstWeight.observe(viewLifecycleOwner, Observer {
            setUpWeight(textViewInit, textViewInitImc, it)
        })
    }

    private fun observeLastWeight() {
        viewModel.lastWeight.observe(viewLifecycleOwner, Observer {
            setUpWeight(textViewCurrent, textViewCurrentImc, it)
        })
    }

    private fun observeLowerWeight() {
        viewModel.lowerWeight.observe(viewLifecycleOwner, Observer {
            setUpWeight(textViewLow, textViewLowImc, it)
        })
    }

    private fun observeHigerWeight() {
        viewModel.higherWeight.observe(viewLifecycleOwner, Observer {
            setUpWeight(textViewHigh, textViewHighImc, it)
        })
    }

    private fun observeUser() {
        viewModel.user.observe(viewLifecycleOwner, Observer {
            textViewName.text = it.name
            textViewAge.text = it.age.toString()
            textViewHeight.text = it.height.toString()
            textViewGenre.text = if (it.gender == User.MALE) {
                LayoutUtil.getString(R.string.male)
            } else {
                LayoutUtil.getString(R.string.female)
            }
        })
    }

    private fun observeError() {
        viewModel.onError.observe(viewLifecycleOwner, Observer {
            showSnackBar(it)
        })
    }

    private fun setUpButtonMore() {
        imageViewMore.setOnClickListener {
            showPopup(it) { menu ->
                when (menu.itemId) {
                    R.id.menu_edit -> {
                        navigateUserFragment()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun showPopup(v: View, onClickPopup: (MenuItem) -> Boolean) {
        PopupMenu(context!!, v).apply {
            setOnMenuItemClickListener {
                onClickPopup(it)
            }
            inflate(R.menu.menu_perfil)
            show()
        }
    }

    private fun navigateUserFragment() {
        val user = viewModel.user.value
        val intent = PerfilFragmentDirections.actionNavigationPerfilToUserEditFragment(user)
        Navigation.findNavController(view!!).navigate(intent)
    }

}