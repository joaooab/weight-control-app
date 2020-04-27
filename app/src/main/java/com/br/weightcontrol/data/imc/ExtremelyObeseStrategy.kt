package com.br.weightcontrol.data.imc

import com.br.weightcontrol.util.LayoutUtil

class ExtremelyObeseStrategy : IMCStrategy() {

    override fun execute(imc: Double): Boolean = imc >= 40.0

    override fun getResult(): String = "Obesidade Grau 3"

    override fun getColor(): Int = LayoutUtil.getColor(android.R.color.black)

}