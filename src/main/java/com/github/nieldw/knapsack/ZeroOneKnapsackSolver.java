package com.github.nieldw.knapsack;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Collections.emptyList;
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

        List<LongWeightItem> solution = findSolutionIteratively(movePointToRight(weightLimit, maxScale), longWeightItems);
        return solution.stream()
                .map(i -> new Item(i.index, new BigDecimal(i.weight).movePointLeft(maxScale), i.value))
                .collect(toList());
    }

    private List<LongWeightItem> findSolutionIteratively(long weightLimit, List<LongWeightItem> items) {
        // The max value achieved with no items and each possible weight, is zero
        Map<SolutionKey, Solution> solutionCache = new HashMap<>();
        for (int i = 0; i <= weightLimit; i++) {
            solutionCache.put(new SolutionKey(0, i), new Solution(BigDecimal.ZERO, emptyList()));
        }

        for (int index = 1; index <= items.size(); index++) {
            for (long currentWeight = 0; currentWeight <= weightLimit; currentWeight++) {
                SolutionKey partialSolutionKey = new SolutionKey(index, currentWeight);
                LongWeightItem item = items.get(index - 1);

                Solution partialSolutionWithOneItemFewer = solutionCache.get(new SolutionKey(index - 1, currentWeight));
                if (item.weight > currentWeight) {
                    // The partial solution up to the current index is the same as the partial solution with one item fewer
                    solutionCache.put(partialSolutionKey, partialSolutionWithOneItemFewer);
                } else {
                    SolutionKey partialSolutionKeyWithoutCurrentItem = new SolutionKey(index - 1, currentWeight - item.weight);
                    Solution partialSolutionWithoutCurrentItem = solutionCache.get(partialSolutionKeyWithoutCurrentItem);
                    BigDecimal valueWithCurrentItemAdded = partialSolutionWithoutCurrentItem.value.add(item.value);

                    if (partialSolutionWithOneItemFewer.value.compareTo(valueWithCurrentItemAdded) < 0) {
                        solutionCache.put(partialSolutionKey, partialSolutionWithoutCurrentItem.add(item));
                    } else {
                        solutionCache.put(partialSolutionKey, partialSolutionWithOneItemFewer);
                    }
                }
            }
        }

        return solutionCache.get(new SolutionKey(items.size(), weightLimit)).solution;
    }

    private long movePointToRight(BigDecimal bigDecimal, Integer n) {
        return bigDecimal.movePointRight(n).longValueExact();
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

        @Override
        public String toString() {
            return "LongWeightItem{" +
                    "index=" + index +
                    ", weight=" + weight +
                    ", value=" + value +
                    '}';
        }
    }

    private class SolutionKey {
        int highestIndex;
        long weightLimit;

        SolutionKey(int highestIndex, long weightLimit) {
            this.highestIndex = highestIndex;
            this.weightLimit = weightLimit;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SolutionKey that = (SolutionKey) o;

            return highestIndex == that.highestIndex && weightLimit == that.weightLimit;
        }

        @Override
        public int hashCode() {
            int result = highestIndex;
            result = 31 * result + (int) (weightLimit ^ (weightLimit >>> 32));
            return result;
        }

        @Override
        public String toString() {
            return "SolutionKey{" +
                    "highestIndex=" + highestIndex +
                    ", weightLimit=" + weightLimit +
                    '}';
        }
    }

    private class Solution {
        BigDecimal value;
        List<LongWeightItem> solution;

        Solution(BigDecimal value, List<LongWeightItem> solution) {
            this.value = value;
            this.solution = solution;
        }

        Solution add(LongWeightItem item) {
            List<LongWeightItem> solution = new ArrayList<>(this.solution.size() + 1);
            solution.addAll(this.solution);
            solution.add(item);
            return new Solution(this.value.add(item.value), solution);
        }

        @Override
        public String toString() {
            return "Solution{" +
                    "value=" + value +
                    ", solution=" + solution +
                    '}';
        }
    }
}
