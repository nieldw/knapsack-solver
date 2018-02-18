package com.github.nieldw.packer

import com.github.nieldw.knapsack.Item
import com.github.nieldw.knapsack.KnapsackSolution
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal.ZERO
import kotlin.test.expect

internal class IndexListSolutionPrinterTest {
    private lateinit var solutionPrinter: SolutionPrinter

    @BeforeEach
    fun `set up printer`() {
        this.solutionPrinter = IndexListSolutionPrinter()
    }

    @Test
    fun `should print empty string for empty solution list`() {
        expect("", { solutionPrinter.print(emptyList()) })
    }

    @Test
    fun `should print solutions correctly`() {
        expect(
                "1\n" +
                        "2,4\n" +
                        "-\n" +
                        "2\n", {
            solutionPrinter.print(listOf(
                    KnapsackSolution(listOf(Item(1, ZERO, ZERO))),
                    KnapsackSolution(listOf(Item(2, ZERO, ZERO), Item(4, ZERO, ZERO))),
                    KnapsackSolution(emptyList()),
                    KnapsackSolution(listOf(Item(2, ZERO, ZERO)))))
        })
    }
}