package com.github.nieldw.packer;

import com.github.nieldw.knapsack.Item;
import com.github.nieldw.knapsack.KnapsackSolution;

import java.util.List;

/**
 * Prints the indexes of a list of solutions, one per line. Prints a dash if there's no solution.
 * <p>
 * For example
 * 4
 * -
 * 2,7
 * 8,9
 */
public class IndexListSolutionPrinter implements SolutionPrinter {
    @Override
    public String print(List<KnapsackSolution> solution) {
        StringBuilder output = new StringBuilder();
        for (KnapsackSolution knapsackSolution : solution) {
            StringBuilder line = new StringBuilder();
            List<Item> items = knapsackSolution.getItems();
            if (items.isEmpty()) {
                output.append("-\n");
                continue;
            }

            for (Item item : items) {
                if (line.length() == 0) {
                    line.append(item.getIndex());
                } else {
                    line.append(",").append(item.getIndex());
                }
            }
            output.append(line).append("\n");
        }
        return output.toString();
    }
}
