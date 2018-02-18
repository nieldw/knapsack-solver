package com.github.nieldw.knapsack.constraints;

import com.github.nieldw.knapsack.KnapsackProblem;

/**
 * A constraint on a {@link KnapsackProblem}.
 */
public interface Constraint {
    /**
     * Check that the constraint is not violated by the given {@link KnapsackProblem}.
     *
     * @param problem The {@link KnapsackProblem} to check
     * @throws IllegalArgumentException If the constraint is violated
     */
    void check(KnapsackProblem problem) throws IllegalArgumentException;
}
