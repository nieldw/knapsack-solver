package com.github.nieldw.packer

import com.github.nieldw.knapsack.Item
import com.github.nieldw.knapsack.KnapsackProblem
import com.github.nieldw.knapsack.KnapsackSolution
import com.github.nieldw.knapsack.KnapsackSolver
import com.github.nieldw.knapsack.constraints.KnapsackProblemConstraintViolationException
import org.junit.jupiter.api.Test
import java.math.BigDecimal.ONE
import java.math.BigDecimal.TEN
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.expect

internal class PackingControllerTest {

    @Test
    fun `it should parse, solve and print`() {
        val expectedString = "raw problem string"
        val expectedKnapsackProblem = KnapsackProblem(ONE, listOf(Item(1, TEN, TEN)))
        val expectedSolution = KnapsackSolution(listOf(Item(1, TEN, TEN)))
        val controller = PackingController(
                KnapsackProblemParser { rawString ->
                    assertEquals(rawString, expectedString)
                    listOf(expectedKnapsackProblem)
                },
                SolutionPrinter { solutions ->
                    assertEquals(solutions, listOf(expectedSolution))
                    "expected output"
                },
                KnapsackSolver { knapsackProblem ->
                    assertEquals(knapsackProblem, expectedKnapsackProblem)
                    expectedSolution
                })

        expect("expected output", {
            controller.solve(expectedString)
        })
    }

    @Test
    fun `it should rethrow IllegalArgumentException as APIException`() {
        val knapsackProblem = KnapsackProblem(ONE, listOf(Item(1, TEN, TEN)))
        val controller = PackingController(
                KnapsackProblemParser { listOf(knapsackProblem) },
                SolutionPrinter { "" },
                KnapsackSolver { throw IllegalArgumentException("dummy exception") })

        assertFailsWith<APIException> { controller.solve("") }
    }

    @Test
    fun `it should rethrow KnapsackProblemConstraintViolationException as APIException`() {
        val controller = PackingController(
                KnapsackProblemParser { throw KnapsackProblemConstraintViolationException("dummy exception") },
                SolutionPrinter { "" },
                KnapsackSolver { KnapsackSolution(emptyList()) })

        assertFailsWith<APIException> { controller.solve("") }
    }
}