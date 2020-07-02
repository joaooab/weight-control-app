package com.br.weightcontrol.util

import com.br.weightcontrol.data.imc.*
import com.br.weightcontrol.data.user.Session
import com.br.weightcontrol.data.user.User
import com.br.weightcontrol.data.weight.Weight

object IMCUtil {

    private val strategyList = listOf(
        UnderweightStrategy(),
        HealthyStrategy(),
        OverweightStrategy(),
        ObeseStrategy(),
        SevereObeseStrategy(),
        ExtremelyObeseStrategy()
    )

    fun calculate(weight: Weight): Double {
        val user = Session.user.value ?: throw IllegalArgumentException("User null")
        val height = user.height.toDouble() / 100
        return if (height == 0.0) {
            return 0.0
        } else {
            weight.weight / (height * height)
        }
    }

    fun calculateIdealWeight(): Double {
        val user = Session.user.value ?: throw IllegalArgumentException("User null")
        val height = user.height.toDouble() / 100
        return if (height == 0.0) {
            return 0.0
        } else {
            val idealIMC = getIdealIMC(user.gender)
            idealIMC * (height * height)
        }
    }

    private fun getIdealIMC(gender: String): Double {
        return if (gender == User.MALE) {
            23.00
        } else {
            21.00
        }
    }

    fun getIMC(weight: Weight): IMC {
        val imc = calculate(weight)
        for (strategy in strategyList) {
            if (strategy.isIMC(imc)) {
                return strategy.getIMC()
            }
        }
        throw IllegalArgumentException("Peso inválido")
    }
}