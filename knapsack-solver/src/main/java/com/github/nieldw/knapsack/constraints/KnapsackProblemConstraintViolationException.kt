package com.github.nieldw.knapsack.constraints

/**
 * Exception thrown when a constraint is violated.
 */
class KnapsackProblemConstraintViolationException(override val message: String) : RuntimeException(message)