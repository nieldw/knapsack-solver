package com.github.nieldw.knapsack;

import com.github.nieldw.knapsack.constraints.Constraint;

import java.util.Collections;
import java.util.List;

import static java.util.Optional.ofNullable;

/**
 * Abstract class that provides support for checking {@link KnapsackProblem} {@link Constraint}s.
 */
abstract class ConstrainedKnapsackSolver implements KnapsackSolver {
    private List<Constraint> constraints;

    /**
     * Constructs a new {@link ConstrainedKnapsackSolver}.
     *
     * @param constraints The {@link Constraint}s applied to problems by this {@link KnapsackSolver}
     */
    ConstrainedKnapsackSolver(List<Constraint> constraints) {
        this.constraints = ofNullable(constraints).orElse(Collections.emptyList());
    }

    void checkConstraints(KnapsackProblem problem) {
        constraints.forEach(constraint -> constraint.check(problem));
    }
}
