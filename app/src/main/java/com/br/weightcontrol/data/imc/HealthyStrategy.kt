package com.br.weightcontrol.data.imc

import com.br.weightcontrol.util.LayoutUtil

class HealthyStrategy : IMCStrategy() {

    override fun execute(imc: Double): Boolean = imc in 18.5..24.9

    override fun getResult(): String = "Peso normal"

    override fun getColor(): Int = LayoutUtil.getColor(android.R.color.holo_green_dark)

}