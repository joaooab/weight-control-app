package com.br.weightcontrol.util

import org.junit.Test


internal class DateExtensionKtTest {

    @Test
    fun `Given old date When parse to new date Then return expected`() {
        assert(parseOldDateToNewDate("27/02/2023") == "2023-02-27")
        assert(parseOldDateToNewDate("26/02/2023") == "2023-02-26")
    }
}
