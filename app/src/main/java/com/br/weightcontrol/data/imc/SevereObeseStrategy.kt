package com.br.weightcontrol.data.imc

import com.br.weightcontrol.util.LayoutUtil

class SevereObeseStrategy : IMCStrategy() {

    override fun execute(imc: Double): Boolean = imc in 35.0..39.9

    override fun getResult(): String = "Obesidade grau 2"

    override fun getColor(): Int = LayoutUtil.getColor(android.R.color.holo_purple)

}