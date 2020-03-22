package com.br.weightcontrol.extension

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.br.weightcontrol.R
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Fragment.supportFragmentManager(execute: FragmentManager.() -> Unit) {
    val supportFragmentManager = activity?.supportFragmentManager
        ?: throw IllegalArgumentException("Activity null")
    execute(supportFragmentManager)
}

fun Fragment.showBottomNavigationView() {
    activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.let {
        it.visibility = View.VISIBLE
    }
}

fun Fragment.hideBottomNavigationView() {
    activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.let {
        it.visibility = View.GONE
    }
}
