# Knapsack Solver
[![Build Status](https://travis-ci.org/nieldw/knapsack-solver.svg?branch=master)](https://travis-ci.org/nieldw/knapsack-solver)

A simple Java implementation of solver(s) for the [Knapsack Problem](https://en.wikipedia.org/wiki/Knapsack_problem).

The KnapsackSolver interface provides these affordances:
1. The knapsack's weight limit may be a BigDecimal
1. The items' weight may be a BigDecimal
1. The items' value may be a BigDecimal

A solver implementation must cater for these affordances. The affordance allows BigDecimal weights, because BigDecimals
allow an exact representation of a number.

## 0/1 Knapsack Solver

The 0/1 Knapsack Solver adjusts the weight of all items by multiplying the weight of each item by 10 times the largest
number of decimal places among all the weights, thereby reducing the problem to that of integer weighted items.
