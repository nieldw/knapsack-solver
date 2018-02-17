package com.github.nieldw.knapsack

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.BigDecimal.ONE
import java.math.BigDecimal.TEN
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
            solver.solve(ONE, listOf(Item(1, ONE, ONE), Item(0, ONE, ONE)))
        }
    }

    @Test
    fun `the solution for the empty set is an empty set`() {
        expect(emptyList(), {
            solver.solve(ONE, emptyList())
        })
    }

    @Test
    fun `the solution for a singleton set with weight greater than the limit is an empty set`() {
        expect(emptyList(), {
            solver.solve(ONE, listOf(Item(1, TEN, TEN)))
        })
    }

    @Test
    fun `the solution for a singleton set with weight less than the limit is that set`() {
        val expectedItem = Item(1, weight("4.5"), value("10.0"))
        expect(listOf(expectedItem), {
            solver.solve(weight("5.0"), listOf(expectedItem))
        })
    }

    @Test
    fun `the solution for a singleton set with weight equal to the limit is that set`() {
        val expectedItem = Item(1, weight("5.0"), value("10.0"))
        expect(listOf(expectedItem), {
            solver.solve(weight("5.0"), listOf(expectedItem))
        })
    }

    @Test
    fun `the solution for two items, with the second larger than the weight limit, is the smaller item`() {
        val expectedItem = Item(1, weight("4.5"), value("10.0"))
        val otherItem = Item(2, weight("5.5"), value("10.0"))
        expect(listOf(expectedItem), {
            solver.solve(weight("5.0"), listOf(expectedItem, otherItem))
        })
    }

    @Test
    fun `the solution for two items, with weight of the first equal to the weight limit, is the smaller item`() {
        val expectedItem = Item(1, weight("4.5"), value("10.0"))
        val otherItem = Item(2, weight("5.5"), value("10.0"))
        expect(listOf(expectedItem), {
            solver.solve(weight("5.0"), listOf(expectedItem, otherItem))
        })
    }

    @Test
    fun `solve for two items, both included`() {
        val expectedItem1 = Item(1, weight("2.5"), value("10.0"))
        val expectedItem2 = Item(2, weight("2.5"), value("10.0"))
        expect(listOf(expectedItem1, expectedItem2), {
            solver.solve(weight("5.0"), listOf(expectedItem1, expectedItem2))
        })
    }

    @Test
    fun `solve for three items, first two included, by weight only`() {
        val expectedItem1 = Item(1, weight("2.5"), value("10.0"))
        val expectedItem2 = Item(2, weight("2.5"), value("10.0"))
        val otherItem = Item(3, weight("5.5"), value("10.0"))
        expect(listOf(expectedItem1, expectedItem2), {
            solver.solve(weight("5.0"), listOf(expectedItem1, expectedItem2, otherItem))
        })
    }

    @Test
    fun `solve for three items, last two included, by weight only`() {
        val otherItem = Item(1, weight("5.5"), value("10.0"))
        val expectedItem1 = Item(2, weight("2.5"), value("10.0"))
        val expectedItem2 = Item(3, weight("2.5"), value("10.0"))
        expect(listOf(expectedItem1, expectedItem2), {
            solver.solve(weight("5.0"), listOf(otherItem, expectedItem1, expectedItem2))
        })
    }

    @Disabled
    @Test
    fun `the solution for two items, with combined weight exceeding limit, is more valuable item`() {
        val moreValuableItem = Item(1, weight("4.5"), value("20.0"))
        val lessValuableItem = Item(2, weight("3.5"), value("10.0"))
        expect(listOf(moreValuableItem), {
            solver.solve(weight("5.0"), listOf(moreValuableItem, lessValuableItem))
        })
    }

    private fun weight(string: String) = BigDecimal(string)
    private fun value(string: String) = BigDecimal(string)
}