package com.github.nieldw.knapsack.constraints;

import com.github.nieldw.knapsack.KnapsackProblem;

import java.math.BigDecimal;

/**
 * A {@link Constraint} on the maximum value of an item.
 */
public class ItemValueConstraint implements Constraint {

    private BigDecimal maxValue;

    /**
     * @param maxValue Maximum item value, inclusive.
     */
    public ItemValueConstraint(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void check(KnapsackProblem problem) throws KnapsackProblemConstraintViolationException {
        problem.getItems().forEach(item -> {
            if (maxValue.compareTo(item.getValue()) < 0) {
                throw new KnapsackProblemConstraintViolationException(String.format("Item %d exceeds the maximum value limit for an item", item.getIndex()));
            }
        });
    }
}
