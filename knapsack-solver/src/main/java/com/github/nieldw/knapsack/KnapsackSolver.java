package com.github.nieldw.knapsack;

import java.math.BigDecimal;
import java.util.List;

/**
 * An implementation of a knapsack solver algorithm.
 */
public interface KnapsackSolver {
    /**
     * Solve the knapsack problem for the given weight limit and set of items.
     *
     * @param weightLimit The knapsack capacity
     * @param items The set of {@link Item}s to select from
     * @return A set of {@link Item}s for the given problem
     * @throws IllegalArgumentException If an item with {@link Item#index} less than 1 is found
     */
    // TODO : Inline
    default List<Item> solve(BigDecimal weightLimit, List<Item> items) throws IllegalArgumentException {
        return this.solve(new KnapsackProblem(weightLimit, items));
    }

    /**
     * Solve the given {@link KnapsackProblem}.
     *
     * @param problem The {@link KnapsackProblem}
     * @return A set of {@link Item}s for the given problem
     * @throws IllegalArgumentException If an item with {@link Item#index} less than 1 is found
     */
    List<Item> solve(KnapsackProblem problem) throws IllegalArgumentException;
}
