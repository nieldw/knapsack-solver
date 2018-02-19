# Knapsack Problem Tools
This module provides a number of tools for dealing with knapsack problems.

## Packing Controller
The `PackingController` is the heart of the module and controls how problems are parsed, solved and printed, through the
`KnapsackProblemParser`, `KnapsackSolver` and `SolutionPrinter` interfaces, respectively. All application layer components
should use the PackingController.

## Packer
An example application, `Packer`, is provided, which reads the contents of a file with knapsack problems, and hands it to 
an appropriately configured `PackingController` for solving.

The `PackingController` is configured by dependency injection (DI) to simplify testing and configuration.

## 0/1 Knapsack Problem samples
A few samples of the 0/1 Knapsack Problem with corresponding solutions can be found in the test resources directory of
this module, and can be seen in action in the `PackerTest`.
