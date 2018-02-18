package com.github.nieldw.knapsack.constraints

import com.github.nieldw.knapsack.KnapsackProblem
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertFailsWith

class KnapsackWeightConstraintTest {
    @Test
    fun `should pass with weight below or equal to constraint`() {
        KnapsackWeightConstraint(BigDecimal.TEN).check(KnapsackProblem(BigDecimal.ONE, emptyList()))
        KnapsackWeightConstraint(BigDecimal.TEN).check(KnapsackProblem(BigDecimal.TEN, emptyList()))
    }

    @Test
    fun `should fail with weight above constraint`() {
        assertFailsWith<KnapsackProblemConstraintViolationException> {
            KnapsackWeightConstraint(BigDecimal.ONE).check(KnapsackProblem(BigDecimal.TEN, emptyList()))
        }
    }
}