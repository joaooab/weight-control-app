package com.br.weightcontrol.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.br.weightcontrol.R
import kotlinx.android.synthetic.main.card_view_weight.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        viewModel.weight.observe(this, Observer {
            textViewWeight.text = it
        })

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floatingActionButton.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle(R.string.register)
                .setCancelable(false)
                .setPositiveButton(R.string.save) { _, _ ->
                }
                .setNegativeButton(R.string.cancel) { _, _ ->
                }
                .create()
                .show()
        }
    }
}