package com.github.nieldw.knapsack.constraints

import com.github.nieldw.knapsack.Item
import com.github.nieldw.knapsack.KnapsackProblem
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertFailsWith

class ItemWeightConstraintTest {
    @Test
    fun `should pass with weight below or equal to constraint`() {
        val item = Item(1, BigDecimal.ONE, BigDecimal.ZERO)
        ItemWeightConstraint(BigDecimal.ONE).check(KnapsackProblem(BigDecimal.ZERO, listOf(item)))
        ItemWeightConstraint(BigDecimal.TEN).check(KnapsackProblem(BigDecimal.ZERO, listOf(item)))
    }

    @Test
    fun `should fail with weight above constraint`() {
        val item = Item(1, BigDecimal.TEN, BigDecimal.ZERO)
        assertFailsWith<KnapsackProblemConstraintViolationException> {
            ItemWeightConstraint(BigDecimal.ONE).check(KnapsackProblem(BigDecimal.ZERO, listOf(item)))
        }
    }
}