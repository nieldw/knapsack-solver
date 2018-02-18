package com.github.nieldw.packer;

import com.github.nieldw.knapsack.KnapsackProblem;

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
     */
    List<KnapsackProblem> parse(String inputString);
}
