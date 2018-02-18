package com.github.nieldw.knapsack;

import java.util.List;

/**
 * An implementation of a knapsack solver algorithm.
 */
public interface KnapsackSolver {

    /**
     * Solve the given {@link KnapsackProblem}.
     *
     * @param problem The {@link KnapsackProblem}
     * @return A set of {@link Item}s for the given problem
     * @throws IllegalArgumentException If an item with {@link Item#index} less than 1 is found
     */
    List<Item> solve(KnapsackProblem problem) throws IllegalArgumentException;
}
