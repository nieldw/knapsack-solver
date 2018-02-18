package com.github.nieldw.knapsack.constraints;

import com.github.nieldw.knapsack.KnapsackProblem;

public class ItemListSizeConstraint implements Constraint {
    private int maximumListSize;

    public ItemListSizeConstraint(int maximumListSize) {
        this.maximumListSize = maximumListSize;
    }

    @Override
    public void check(KnapsackProblem problem) throws KnapsackProblemConstraintViolationException {
        if (maximumListSize < problem.getItems().size()) {
            throw new KnapsackProblemConstraintViolationException("The list of items exceeds the allowed maximum number");
        }
    }
}
