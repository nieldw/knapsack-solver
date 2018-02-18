package com.github.nieldw.packer;

import com.github.nieldw.knapsack.KnapsackProblem;

/**
 * An object that knows how to parse a particular format of a {@link KnapsackProblem}.
 */
public interface KnapsackProblemParser {
    /**
     * Parse the problem.
     *
     * @param inputString The raw problem input
     * @return The defined {@link KnapsackProblem}
     */
    KnapsackProblem parse(String inputString);
}
