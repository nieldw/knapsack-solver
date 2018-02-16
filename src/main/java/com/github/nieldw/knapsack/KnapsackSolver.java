package com.github.nieldw.knapsack;

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
    List<Item> solve(Double weightLimit, List<Item> items) throws IllegalArgumentException;
}
