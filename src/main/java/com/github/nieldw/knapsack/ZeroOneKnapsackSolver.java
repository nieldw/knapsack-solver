package com.github.nieldw.knapsack;

import java.util.Collections;
import java.util.List;

import static java.lang.String.format;

/**
 * A solver for the 0/1 knapsack problem.
 */
public class ZeroOneKnapsackSolver implements KnapsackSolver {
    @Override
    public List<Item> solve(Double weightLimit, List<Item> items) throws IllegalArgumentException {
        items.forEach(item -> {
            if (item.getIndex() < 1) {
                throw new IllegalArgumentException(format("All items must have index > 1. Found index %d", item.getIndex()));
            }
        });
        return Collections.emptyList();
    }
}
