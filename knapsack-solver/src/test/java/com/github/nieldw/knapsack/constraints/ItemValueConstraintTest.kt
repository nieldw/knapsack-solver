package com.github.nieldw.knapsack.constraints

import com.github.nieldw.knapsack.Item
import com.github.nieldw.knapsack.KnapsackProblem
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertFailsWith

class ItemValueConstraintTest {
    @Test
    fun `should pass with value below or equal to constraint`() {
        val item = Item(1, BigDecimal.ZERO, BigDecimal.ONE)
        ItemValueConstraint(BigDecimal.ONE).check(KnapsackProblem(BigDecimal.ZERO, listOf(item)))
        ItemValueConstraint(BigDecimal.TEN).check(KnapsackProblem(BigDecimal.ZERO, listOf(item)))
    }

    @Test
    fun `should fail with value above constraint`() {
        val item = Item(1, BigDecimal.ZERO, BigDecimal.TEN)
        assertFailsWith<KnapsackProblemConstraintViolationException> {
            ItemValueConstraint(BigDecimal.ONE).check(KnapsackProblem(BigDecimal.ZERO, listOf(item)))
        }
    }
}