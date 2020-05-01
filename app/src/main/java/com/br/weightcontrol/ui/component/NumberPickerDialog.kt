package com.br.weightcontrol.ui.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.br.weightcontrol.R
import com.br.weightcontrol.data.weight.Weight
import kotlinx.android.synthetic.main.dialog_number_picker.*

class NumberPickerDialog : DialogFragment() {

    private lateinit var onFinished: (weight: Weight) -> Unit
    private lateinit var weight: Weight
    private lateinit var title: String

    companion object {
        private const val DEFAULT_WEIGHT = 50.0
        private const val MIN_VALUE_NUMBER_PICKER_PRIMARY = 20
        private const val MAX_VALUE_NUMBER_PICKER_PRIMARY = 200
        private const val MIN_VALUE_NUMBER_PICKER_SECONDARY = 0
        private const val MAX_VALUE_NUMBER_PICKER_SECONDARY = 9

        fun newInstance(
            title: String,
            weight: Weight? = null,
            onFinished: (weight: Weight) -> Unit
        ): NumberPickerDialog {
            val dialog = NumberPickerDialog().apply {
                this.weight = weight ?: Weight(weight = DEFAULT_WEIGHT)
                this.onFinished = onFinished
                this.title = title
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
        return inflater.inflate(R.layout.dialog_number_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTitle()
        setUpNumberPickerPrimary()
        setUpNumberPickerSecondary()
        setupButtonAction()
    }

    private fun setUpTitle() {
        textViewTitle.text = title
    }

    private fun setupButtonAction() {
        buttonAction.setOnClickListener {
            val numberPrimary = numberPickerPrimary.value.toDouble()
            val numberSecondary = numberPickerSecondary.value.toDouble() / 10
            val value = numberPrimary + numberSecondary
            onFinished(Weight(weight = value))
            dialog?.dismiss()
        }
    }

    private fun setUpNumberPickerSecondary() {
        numberPickerSecondary.apply {
            minValue = MIN_VALUE_NUMBER_PICKER_SECONDARY
            maxValue = MAX_VALUE_NUMBER_PICKER_SECONDARY
            value = (weight.weight % 10 * 10).toInt()
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