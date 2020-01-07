package com.br.weightcontrol.ui.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.br.weightcontrol.R
import com.br.weightcontrol.data.Weight
import kotlinx.android.synthetic.main.dialog_number_picker.*

class NumberPickerDialog : DialogFragment() {

    private lateinit var onFinished: (weight: Weight) -> Unit
    private lateinit var weight: Weight

    companion object {
        private const val DEFAULT_WEIGHT = 50.0
        private const val MIN_VALUE_NUMBER_PICKER_PRIMARY = 20
        private const val MAX_VALUE_NUMBER_PICKER_PRIMARY = 200
        private const val MIN_VALUE_NUMBER_PICKER_SECONDARY = 0
        private const val MAX_VALUE_NUMBER_PICKER_SECONDARY = 9

        fun newInstance(
            onFinished: (weight: Weight) -> Unit,
            weight: Weight? = null
        ): NumberPickerDialog {
            val dialog = NumberPickerDialog()
            dialog.weight = weight ?: Weight(DEFAULT_WEIGHT)
            dialog.onFinished = onFinished
            return dialog
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            900,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_number_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpNumberPickerPrimary()
        setUpNumberPickerSecondary()
        setupButtonAction()
    }

    private fun setupButtonAction() {
        buttonAction.setOnClickListener {
            val numberPrimary = numberPickerPrimary.value.toDouble()
            val numberSecondary = numberPickerSecondary.value.toDouble() / 10
            val value = numberPrimary + numberSecondary
            onFinished(Weight(value))
            dialog.dismiss()
        }
    }

    private fun setUpNumberPickerSecondary() {
        numberPickerSecondary.apply {
            minValue = MIN_VALUE_NUMBER_PICKER_SECONDARY
            maxValue = MAX_VALUE_NUMBER_PICKER_SECONDARY
            value = weight.weight.toInt()
        }
    }

    private fun setUpNumberPickerPrimary() {
        numberPickerPrimary.apply {
            minValue = MIN_VALUE_NUMBER_PICKER_PRIMARY
            maxValue = MAX_VALUE_NUMBER_PICKER_PRIMARY
            value = weight.weight.toInt()
        }
    }
}