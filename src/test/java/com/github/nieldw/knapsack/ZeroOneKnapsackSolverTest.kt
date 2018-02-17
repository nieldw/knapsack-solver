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

    @Test
    fun `the solution for a singleton set with weight less than the limit is that set`() {
        val expectedItem = Item(1, 4.5, 10.0)
        expect(listOf(expectedItem), {
            solver.solve(5.0, listOf(expectedItem))
        })
    }

    @Test
    fun `the solution for a singleton set with weight equal to the limit is that set`() {
        val expectedItem = Item(1, 5.0, 10.0)
        expect(listOf(expectedItem), {
            solver.solve(5.0, listOf(expectedItem))
        })
    }

    @Test
    fun `the solution for two items, with the second larger than the weight limit, is the smaller item`() {
        val expectedItem = Item(1, 4.5, 10.0)
        val otherItem = Item(2, 5.5, 10.0)
        expect(listOf(expectedItem), {
            solver.solve(5.0, listOf(expectedItem, otherItem))
        })
    }

    @Test
    fun `the solution for two items, with weight of the first equal to the weight limit, is the smaller item`() {
        val expectedItem = Item(1, 4.5, 10.0)
        val otherItem = Item(2, 5.5, 10.0)
        expect(listOf(expectedItem), {
            solver.solve(5.0, listOf(expectedItem, otherItem))
        })
    }

    @Test
    fun `solve for two items, both included`() {
        val expectedItem1 = Item(1, 2.5, 10.0)
        val expectedItem2 = Item(2, 2.5, 10.0)
        expect(listOf(expectedItem1, expectedItem2), {
            solver.solve(5.0, listOf(expectedItem1, expectedItem2))
        })
    }

    @Test
    fun `solve for three items, first two included, by weight only`() {
        val expectedItem1 = Item(1, 2.5, 10.0)
        val expectedItem2 = Item(2, 2.5, 10.0)
        val otherItem = Item(3, 5.5, 10.0)
        expect(listOf(expectedItem1, expectedItem2), {
            solver.solve(5.0, listOf(expectedItem1, expectedItem2, otherItem))
        })
    }

    @Test
    fun `solve for three items, last two included, by weight only`() {
        val otherItem = Item(1, 5.5, 10.0)
        val expectedItem1 = Item(2, 2.5, 10.0)
        val expectedItem2 = Item(3, 2.5, 10.0)
        expect(listOf(expectedItem1, expectedItem2), {
            solver.solve(5.0, listOf(otherItem, expectedItem1, expectedItem2))
        })
    }
}