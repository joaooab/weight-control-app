package com.br.weightcontrol.data.imc

import com.br.weightcontrol.util.LayoutUtil

class OverweightStrategy : IMCStrategy() {

    override fun execute(imc: Double): Boolean = imc in 25.0..29.9

    override fun getResult(): String = "Sobrepeso"

    override fun getColor(): Int = LayoutUtil.getColor(android.R.color.holo_orange_dark)

}