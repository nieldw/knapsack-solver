package com.github.nieldw.knapsack;

import java.util.List;

public interface KnapsackSolver {
    List<Item> solve(Double weightLimit, List<Item> items);
}
