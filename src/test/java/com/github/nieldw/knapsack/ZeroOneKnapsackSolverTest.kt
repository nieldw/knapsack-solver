package com.github.nieldw.knapsack

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith
import kotlin.test.expect

class ZeroOneKnapsackSolverTest {
    private lateinit var solver: ZeroOneKnapsackSolver

    @BeforeEach
    fun `initialize solver`() {
        solver = ZeroOneKnapsackSolver()
    }

    @Test
    fun `it should throw IllegalArgumentException if set contains an item with index less than 1`() {
        assertFailsWith<IllegalArgumentException> {
            solver.solve(Double.MAX_VALUE, listOf(Item(1, Double.MAX_VALUE, Double.MAX_VALUE), Item(0, Double.MAX_VALUE, Double.MAX_VALUE)))
        }
    }

    @Test
    fun `the solution for the empty set is an empty set`() {
        expect(emptyList(), {
            solver.solve(Double.MAX_VALUE, emptyList())
        })
    }

    @Test
    fun `the solution for a singleton set with weight greater than the limit is an empty set`() {
        expect(emptyList(), {
            solver.solve(1.0, listOf(Item(1, Double.MAX_VALUE, Double.MAX_VALUE)))
        })
    }
}