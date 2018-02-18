package com.github.nieldw.knapsack

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class ZeroOneKnapsackSolverProblemSamplesTest {
    private lateinit var solver: ZeroOneKnapsackSolver

    @BeforeEach
    fun `initialize solver`() {
        solver = ZeroOneKnapsackSolver()
    }

    @Test
    fun `solve sample problem 1`() {
        val items = listOf(
                Item(1, weight("53.38"), value("45")),
                Item(2, weight("88.62"), value("98")),
                Item(3, weight("78.48"), value("3")),
                Item(4, weight("72.30"), value("76")),
                Item(5, weight("30.18"), value("9")),
                Item(6, weight("46.34"), value("48")))
        val solution = solver.solve(weight("81"), items)
        assertEquals(1, solution.size)
        assertEquals(4, solution[0].index)
    }

    @Test
    fun `solve sample problem 2`() {
        val items = listOf(Item(1, weight("15.3"), value("34")))
        val solution = solver.solve(weight("8"), items)
        assertEquals(0, solution.size)
    }

    @Test
    fun `solve sample problem 3`() {
        val items = listOf(
                Item(1, weight("85.31"), value("29")),
                Item(2, weight("14.55"), value("74")),
                Item(3, weight("3.98"), value("16")),
                Item(4, weight("26.24"), value("55")),
                Item(5, weight("63.69"), value("52")),
                Item(6, weight("76.25"), value("75")),
                Item(7, weight("60.02"), value("74")),
                Item(8, weight("93.18"), value("35")),
                Item(9, weight("89.95"), value("78")))
        val solution = solver.solve(weight("75"), items)
        assertEquals(2, solution.size)
        assertEquals(listOf(2, 7), solution.map { it.index })
    }

    @Test
    fun `solve sample problem 4`() {
        val items = listOf(
                Item(1, weight("90.72"), value("13")),
                Item(2, weight("33.80"), value("40")),
                Item(3, weight("43.15"), value("10")),
                Item(4, weight("37.97"), value("16")),
                Item(5, weight("46.81"), value("36")),
                Item(6, weight("48.77"), value("79")),
                Item(7, weight("81.80"), value("45")),
                Item(8, weight("19.36"), value("79")),
                Item(9, weight("6.76"), value("64")))
        val solution = solver.solve(weight("56"), items)
        assertEquals(2, solution.size)
        assertEquals(listOf(8, 9), solution.map { it.index })
    }

    private fun weight(string: String) = BigDecimal(string)
    private fun value(string: String) = BigDecimal(string)
}