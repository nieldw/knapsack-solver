package com.github.nieldw.knapsack

import java.math.BigDecimal

/**
 * Represents a knapsack problem.
 */
data class KnapsackProblem(val weightLimit: BigDecimal, val items: List<Item>)