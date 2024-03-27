package com.br.weightcontrol.util

private const val DIGITS_BEFORE_DECIMAL = 3
private const val DIGITS_AFTER_DECIMAL = 1
private const val DELIMITER = '.'

fun getValidatedDecimalNumber(
    text: String,
    digitsBeforeDecimal: Int = DIGITS_BEFORE_DECIMAL,
    digitsAfterDecimal: Int = DIGITS_AFTER_DECIMAL,
): String {
    val filteredChars = text.filterIndexed { index, c ->
        c.isDigit()
                || (c == '.' && index != 0 && text.indexOf(DELIMITER) == index)
                || (c == '.' && index != 0 && text.count { it == DELIMITER } <= 1)
    }

    return if (filteredChars.count { it == '.' } == 1) {
        val beforeDecimal = filteredChars.substringBefore(DELIMITER)
        val afterDecimal = filteredChars.substringAfter(DELIMITER)
        beforeDecimal.take(digitsBeforeDecimal) + "." + afterDecimal.take(digitsAfterDecimal)
    } else {
        filteredChars.take(digitsBeforeDecimal)
    }
}

fun onChangeWithLimit(text: String, limit: Int): String {
    return if (text.length <= limit) text
    else text.take(limit)
}