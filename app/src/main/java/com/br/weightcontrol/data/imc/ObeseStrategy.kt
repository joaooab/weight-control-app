package com.br.weightcontrol.data.imc

import com.br.weightcontrol.util.LayoutUtil

class ObeseStrategy : IMCStrategy() {

    override fun execute(imc: Double): Boolean = imc in 30.0..34.9

    override fun getResult(): String = "Obesidade grau 1"

    override fun getColor(): Int = LayoutUtil.getColor(android.R.color.holo_red_dark)

}