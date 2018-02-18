package com.github.nieldw.knapsack;

import com.github.nieldw.knapsack.constraints.Constraint;

import java.util.Collections;
import java.util.List;

import static java.util.Optional.ofNullable;

/**
 * Abstract class that provides support for checking {@link KnapsackProblem} {@link Constraint}s.
 */
public abstract class KnapsackProblemConstraintSupport {
    private List<Constraint> constraints;

    /**
     * Constructs a new {@link KnapsackProblemConstraintSupport}.
     *
     * @param constraints The {@link Constraint}s applied to problems by this object
     */
    protected KnapsackProblemConstraintSupport(List<Constraint> constraints) {
        this.constraints = ofNullable(constraints).orElse(Collections.emptyList());
    }

    public void checkConstraints(KnapsackProblem problem) {
        constraints.forEach(constraint -> constraint.check(problem));
    }
}
