package com.github.nieldw.knapsack;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import static java.lang.String.format;

/**
 * A solver for the 0/1 knapsack problem.
 */
public class ZeroOneKnapsackSolver implements KnapsackSolver {
    @Override
    public List<Item> solve(@NotNull Double weightLimit, @NotNull List<Item> items) throws IllegalArgumentException {
        items.forEach(item -> {
            if (item.getIndex() < 1) {
                throw new IllegalArgumentException(format("All items must have index > 1. Found index %d", item.getIndex()));
            }
        });

        return solveForWeightAndSet(weightLimit, items);
    }

    private List<Item> solveForWeightAndSet(Double weightLimit, List<Item> items) {
        if (items.isEmpty()) {
            return Collections.emptyList();
        }

        if (items.get(items.size() - 1).getWeight() > weightLimit) {
            return solveForWeightAndSet2(weightLimit, items.subList(0, items.size() - 1));
        } else {
            return items;
        }
    }

    private List<Item> solveForWeightAndSet2(Double weightLimit, List<Item> items) {
        if (items.isEmpty()) {
            return Collections.emptyList();
        } else {
            return items;
        }
    }
}
