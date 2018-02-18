package com.github.nieldw.knapsack.constraints

import com.github.nieldw.knapsack.Item
import com.github.nieldw.knapsack.KnapsackProblem
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertFailsWith

class ItemListSizeConstraintTest {
    @Test
    fun `should pass if the maximum item list size is not exceeded`() {
        val constraint = ItemListSizeConstraint(2)
        val irrelevantItem = Item(1, BigDecimal.ONE, BigDecimal.ONE)

        constraint.check(KnapsackProblem(BigDecimal.ZERO, listOf(irrelevantItem)))
        constraint.check(KnapsackProblem(BigDecimal.ZERO, listOf(irrelevantItem, irrelevantItem)))
    }

    @Test
    fun `should throw KnapsackProblemConstraintViolationException if the maximum item list size is exceeded`() {
        val constraint = ItemListSizeConstraint(2)
        val irrelevantItem = Item(1, BigDecimal.ONE, BigDecimal.ONE)

        assertFailsWith<KnapsackProblemConstraintViolationException> {
            constraint.check(KnapsackProblem(BigDecimal.ZERO, listOf(irrelevantItem, irrelevantItem, irrelevantItem)))
        }
    }
}