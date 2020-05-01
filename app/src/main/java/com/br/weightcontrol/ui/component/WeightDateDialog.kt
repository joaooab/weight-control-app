package com.br.weightcontrol.ui.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.br.weightcontrol.R
import com.br.weightcontrol.data.weight.Weight
import com.br.weightcontrol.extension.showSnackBar
import kotlinx.android.synthetic.main.dialog_weight_date.*
import java.util.*

class WeightDateDialog : DialogFragment() {

    private lateinit var onFinished: (weight: Weight) -> Unit

    companion object {
        fun newInstance(
            onFinished: (weight: Weight) -> Unit
        ): WeightDateDialog {
            val dialog = WeightDateDialog().apply {
                this.onFinished = onFinished
            }

            return dialog
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            800,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_weight_date, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTextDatePicker.setData(Calendar.getInstance())
        buttonAction.setOnClickListener {
            val date = editTextDatePicker.getData() ?: Calendar.getInstance()
            if (date.after(Calendar.getInstance())) {
                showSnackBar(constraintLayout, "Não é possível adicionar para data futura")
            } else {
                val weight = editTextWeight.text.toString().toDouble()
                onFinished(Weight(weight = weight, date = date))
                dialog?.dismiss()
            }
        }
    }

}