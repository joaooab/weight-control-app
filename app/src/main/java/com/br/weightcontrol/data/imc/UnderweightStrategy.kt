package com.br.weightcontrol.data.imc

import com.br.weightcontrol.util.LayoutUtil

class UnderweightStrategy : IMCStrategy() {

    override fun execute(imc: Double): Boolean = imc <= 18.5

    override fun getResult(): String = "Abaixo do peso"

    override fun getColor(): Int = LayoutUtil.getColor(android.R.color.holo_blue_dark)

}