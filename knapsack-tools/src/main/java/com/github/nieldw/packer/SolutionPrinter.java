package com.github.nieldw.packer;

import com.github.nieldw.knapsack.Item;

import java.util.List;

/**
 * An object that knows how to output the solution of a {@link com.github.nieldw.knapsack.KnapsackProblem}.
 */
public interface SolutionPrinter {
    /**
     * Output the solution.
     *
     * @param solution The solution to a {@link com.github.nieldw.knapsack.KnapsackProblem}
     * @return The String representation of the solution
     */
    String print(List<Item> solution);
}
