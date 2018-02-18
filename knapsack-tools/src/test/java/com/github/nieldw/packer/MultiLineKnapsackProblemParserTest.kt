package com.github.nieldw.packer

import com.github.nieldw.knapsack.Item
import com.github.nieldw.knapsack.KnapsackProblem
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertFailsWith
import kotlin.test.expect

internal class MultiLineKnapsackProblemParserTest {
    private lateinit var parser: KnapsackProblemParser

    @BeforeEach
    fun `set up parser`() {
        this.parser = MultiLineKnapsackProblemParser()
    }

    @Test
    fun `should parse one correct line successfully`() {
        val line = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)"
        val items = listOf(
                Item(1, weight("53.38"), value("45")),
                Item(2, weight("88.62"), value("98")),
                Item(3, weight("78.48"), value("3")),
                Item(4, weight("72.30"), value("76")),
                Item(5, weight("30.18"), value("9")),
                Item(6, weight("46.34"), value("48")))
        val knapsackProblem = KnapsackProblem(weight("81"), items)
        expect(knapsackProblem, {
            parser.parse(line)
        })
    }

    @Test
    fun `should throw APIException if line is misformed`() {
        val line = "11 : (bla)"
        assertFailsWith<APIException> { parser.parse(line) }
    }

    private fun weight(string: String) = BigDecimal(string)
    private fun value(string: String) = BigDecimal(string)
}