package com.br.weightcontrol.bmi.domain

import com.br.weightcontrol.bmi.domain.strategy.*
import org.junit.Test


internal class BMICalculatorKtTest {

    @Test
    fun `When calculateBMI Then return expected`() {
        assert(calculateBMI(80.0, 1.80).value == 24.7)
        assert(calculateBMI(60.0, 1.60).value == 23.4)
        assert(calculateBMI(65.0, 1.63).value == 24.5)
        assert(calculateBMI(59.0, 1.90).value == 16.3)
        assert(calculateBMI(98.0, 1.53).value == 41.9)
        assert(calculateBMI(114.0, 2.03).value == 27.7)
    }

    @Test
    fun `When findStrategy by bmi Then return expected`() {
        assert(findBMIStrategy(11.0) is ThinnessSevereStrategy)
        assert(findBMIStrategy(15.99) is ThinnessSevereStrategy)

        assert(findBMIStrategy(16.0) is ThinnessModerateStrategy)
        assert(findBMIStrategy(16.99) is ThinnessModerateStrategy)

        assert(findBMIStrategy(17.0) is ThinnessMildStrategy)
        assert(findBMIStrategy(18.49) is ThinnessMildStrategy)

        assert(findBMIStrategy(18.5) is NormalStrategy)
        assert(findBMIStrategy(24.99) is NormalStrategy)

        assert(findBMIStrategy(25.0) is OverweightStrategy)
        assert(findBMIStrategy(29.99) is OverweightStrategy)

        assert(findBMIStrategy(30.0) is ObeseStrategy)
        assert(findBMIStrategy(34.99) is ObeseStrategy)

        assert(findBMIStrategy(35.0) is ObeseSevereStrategy)
        assert(findBMIStrategy(39.99) is ObeseSevereStrategy)

        assert(findBMIStrategy(40.0) is ObeseExtremeStrategy)
    }
}
