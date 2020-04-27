package com.br.weightcontrol.data.imc

abstract class IMCStrategy {

    protected var imc: Double = 0.0

    fun isIMC(imc: Double): Boolean {
        this.imc = imc
        return execute(imc)
    }

    fun getIMC() = IMC(imc, getResult(), getColor())

    protected abstract fun execute(imc: Double): Boolean

    protected abstract fun getResult(): String

    protected abstract fun getColor(): Int

}