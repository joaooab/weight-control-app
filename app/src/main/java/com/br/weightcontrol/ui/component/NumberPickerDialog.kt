package com.br.weightcontrol.ui.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.br.weightcontrol.R
import kotlinx.android.synthetic.main.dialog_number_picker.*

class NumberPickerDialog : DialogFragment() {

    companion object {
        fun newInstance() = NumberPickerDialog()
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
        numberPicker.apply {
            minValue = 20
            maxValue = 200
            value = 80
        }
    }
}