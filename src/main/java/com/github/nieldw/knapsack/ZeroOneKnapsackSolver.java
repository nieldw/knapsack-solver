package com.github.nieldw.knapsack;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

/**
 * A solver for the 0/1 knapsack problem.
 */
public class ZeroOneKnapsackSolver implements KnapsackSolver {
    @Override
    public List<Item> solve(@NotNull BigDecimal weightLimit, @NotNull List<Item> items) throws IllegalArgumentException {
        items.forEach(item -> {
            if (item.getIndex() < 1) {
                throw new IllegalArgumentException(format("All items must have index > 1. Found index %d", item.getIndex()));
            }
        });

        Integer maxScale = items.stream().map(i -> i.getWeight().scale()).max(comparingInt(i -> i)).orElse(0);
        List<LongWeightItem> longWeightItems = items.stream()
                .sorted(comparing(Item::getWeight))
                .map(i -> new LongWeightItem(i.getIndex(), movePointToRight(i.getWeight(), maxScale), i.getValue()))
                .collect(toList());

        List<LongWeightItem> solution = solveForWeightAndSet(movePointToRight(weightLimit, maxScale), longWeightItems);
        return solution.stream()
                .map(i -> new Item(i.index, new BigDecimal(i.weight).movePointLeft(maxScale), i.value))
                .collect(toList());
    }

    private List<LongWeightItem> solveForWeightAndSet(long weightLimit, List<LongWeightItem> items) {
        if (items.isEmpty()) {
            return Collections.emptyList();
        }

        if (lastOf(items).weight > weightLimit) {
            return solveForWeightAndSet(weightLimit, items.subList(0, items.size() - 1));
        } else {
            return items;
        }
    }

    private long movePointToRight(BigDecimal bigDecimal, Integer n) {
        return bigDecimal.movePointRight(n).longValueExact();
    }

    private LongWeightItem lastOf(List<LongWeightItem> items) {
        return items.get(items.size() - 1);
    }

    private class LongWeightItem {
        int index;
        long weight;
        BigDecimal value;

        LongWeightItem(int index, long weight, BigDecimal value) {
            this.index = index;
            this.weight = weight;
            this.value = value;
        }
    }
}
