package com.github.nieldw.knapsack;

/**
 * An implementation of a knapsack solver algorithm.
 */
public interface KnapsackSolver {

    /**
     * Solve the given {@link KnapsackProblem}.
     *
     * @param problem The {@link KnapsackProblem}
     * @return A {@link KnapsackSolution}
     * @throws IllegalArgumentException If an item with {@link Item#index} less than 1 is found
     */
    KnapsackSolution solve(KnapsackProblem problem) throws IllegalArgumentException;
}
