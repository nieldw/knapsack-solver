package com.github.nieldw.packer;

import com.github.nieldw.knapsack.KnapsackSolver;
import com.github.nieldw.knapsack.constraints.KnapsackProblemConstraintViolationException;

/**
 * This controller controls interaction between the application layer, and domain layer.
 */
public class PackingController {
    private KnapsackProblemParser parser;
    private SolutionPrinter printer;
    private KnapsackSolver solver;

    /**
     * Construct a {@link PackingController} with the necessary parser, printer and solver.
     *
     * @param parser A {@link KnapsackProblemParser}
     * @param printer A {@link SolutionPrinter}
     * @param solver A {@link KnapsackSolver}
     */
    public PackingController(KnapsackProblemParser parser, SolutionPrinter printer, KnapsackSolver solver) {
        this.parser = parser;
        this.printer = printer;
        this.solver = solver;
    }

    public String solve(String rawProblem) throws KnapsackProblemConstraintViolationException { return ""; }
}
