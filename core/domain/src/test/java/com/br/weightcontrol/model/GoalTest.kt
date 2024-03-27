package com.br.weightcontrol.model

import com.br.weightcontrol.util.today
import com.br.weightcontrol.util.todayAsString
import org.junit.Test

class GoalTest {
    @Test
    fun `shouldComplete when goal is to increase weight`() {
        val goal = Goal(start = 70.0, desire = 80.0, createdAt = todayAsString())
        val track = Track(weight = 85.0, createdAt = today())

        val result = goal.shouldComplete(track)

        assert(result)
    }

    @Test
    fun `shouldComplete when goal is to decrease weight`() {
        val goal = Goal(start = 80.0, desire = 70.0, createdAt = todayAsString())
        val track = Track(weight = 65.0, createdAt = today())

        val result = goal.shouldComplete(track)

        assert(result)
    }

    @Test
    fun `should not complete when goal is to increase weight and weight is smaller`() {
        val goal = Goal(start = 70.0, desire = 80.0, createdAt = todayAsString())
        val track = Track(weight = 75.0, createdAt = today())

        val result = goal.shouldComplete(track)

        assert(!result)
    }

    @Test
    fun `should not complete when goal is to decrease weight and weight is greater`() {
        val goal = Goal(start = 80.0, desire = 70.0, createdAt = todayAsString())
        val track = Track(weight = 75.0, createdAt = today())

        val result = goal.shouldComplete(track)

        assert(!result)
    }
}