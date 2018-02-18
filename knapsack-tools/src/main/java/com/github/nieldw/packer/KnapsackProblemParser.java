package com.github.nieldw.packer;

import com.github.nieldw.knapsack.KnapsackProblem;
import com.github.nieldw.knapsack.constraints.KnapsackProblemConstraintViolationException;

import java.util.List;

/**
 * An object that knows how to parse a particular format of a {@link KnapsackProblem}.
 */
public interface KnapsackProblemParser {
    /**
     * Parse the problems.
     *
     * @param inputString The raw problem input
     * @return The defined {@link KnapsackProblem}s
     * @throws APIException If the input is malformed
     * @throws KnapsackProblemConstraintViolationException if a {@link com.github.nieldw.knapsack.constraints.Constraint} is violated
     */
    List<KnapsackProblem> parse(String inputString) throws APIException, KnapsackProblemConstraintViolationException;
}
