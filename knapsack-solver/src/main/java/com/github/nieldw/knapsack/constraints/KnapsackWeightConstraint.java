package com.github.nieldw.knapsack.constraints;

import com.github.nieldw.knapsack.KnapsackProblem;

import java.math.BigDecimal;

/**
 * A {@link Constraint} on the maximum weight of a knapsack.
 */
public class KnapsackWeightConstraint implements Constraint {

    private BigDecimal maxWeight;

    /**
     * @param maxWeight Maximum knapsack weight, inclusive.
     */
    public KnapsackWeightConstraint(BigDecimal maxWeight) {
        this.maxWeight = maxWeight;
    }

    @Override
    public void check(KnapsackProblem problem) throws KnapsackProblemConstraintViolationException {
        if (maxWeight.compareTo(problem.getWeightLimit()) < 0) {
            throw new KnapsackProblemConstraintViolationException("The knapsack weight exceeds the allowed maximum");
        }
    }
}
