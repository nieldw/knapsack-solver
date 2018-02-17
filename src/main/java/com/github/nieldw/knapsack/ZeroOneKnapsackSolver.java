package com.github.nieldw.knapsack;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import static java.lang.String.format;
import static java.util.Comparator.comparingDouble;

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

        items.sort(comparingDouble(Item::getWeight));
        if (last(items).getWeight() > weightLimit) {
            return solveForWeightAndSet(weightLimit, items.subList(0, items.size() - 1));
        } else {
            return items;
        }
    }

    private Item last(List<Item> items) {
        return items.get(items.size() - 1);
    }
}
