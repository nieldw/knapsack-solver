# Knapsack Solver
[![Build Status](https://travis-ci.org/nieldw/knapsack-solver.svg?branch=master)](https://travis-ci.org/nieldw/knapsack-solver)

A simple Java implementation of solver(s) for the [Knapsack Problem](https://en.wikipedia.org/wiki/Knapsack_problem).

The `KnapsackSolver` interface provides these affordances:
1. The knapsack's weight limit may be a `BigDecimal`
1. The items' weight may be a `BigDecimal`
1. The items' value may be a `BigDecimal`

A solver implementation must cater for these affordances. The affordance allows BigDecimal weights, because BigDecimals
allow an exact representation of a number.

## 0/1 Knapsack Solver

A solution to the 0/1 knapsack problem is provided in the `ZeroOneKnapsackSolver`.

The 0/1 Knapsack Solver adjusts the weight of all items by multiplying the weight of each item by 10 times the largest
number of decimal places among all the weights, thereby reducing the problem to that of integer weighted items.

## Constraints

Implementations of `KnapsackProblemConstraintSupport` can be supplied with a list of `Constraints`. A number of 
`Constraint` implementations are provided:

- `KnapsackWeightConstraint`
- `ItemListSizeConstraint`
- `ItemValueConstraint`
- `ItemWeightConstraint`

Constraints are checked at the time of problem parsing to ensure all `KnapsackProblems` are valid.

## Tools

Alongside the `knapsack-solver` module a collection of tools are provided to simplify dealing with knapsack problems. 
Please see the [knapsack-tools](knapsack-tools/README.md) module.