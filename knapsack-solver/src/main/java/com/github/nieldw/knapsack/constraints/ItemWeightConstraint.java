package com.github.nieldw.knapsack.constraints;

import com.github.nieldw.knapsack.KnapsackProblem;

import java.math.BigDecimal;

/**
 * A {@link Constraint} on the maximum weight of an item.
 */
public class ItemWeightConstraint implements Constraint {

    private BigDecimal maxWeight;

    /**
     * @param maxWeight Maximum item weight, inclusive.
     */
    public ItemWeightConstraint(BigDecimal maxWeight) {
        this.maxWeight = maxWeight;
    }

    @Override
    public void check(KnapsackProblem problem) throws KnapsackProblemConstraintViolationException {
        problem.getItems().forEach(item -> {
            if (maxWeight.compareTo(item.getWeight()) < 0) {
                throw new KnapsackProblemConstraintViolationException(String.format("Item %d exceeds the maximum weight limit for an item", item.getIndex()));
            }
        });
    }
}
