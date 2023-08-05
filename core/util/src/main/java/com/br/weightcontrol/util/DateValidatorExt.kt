package com.br.weightcontrol.util

private val todayTimeStamp = today().toLong()

val dateValidatorLowerThanToday: (Long) -> Boolean = { timeStamp ->
    timeStamp <= todayTimeStamp
}