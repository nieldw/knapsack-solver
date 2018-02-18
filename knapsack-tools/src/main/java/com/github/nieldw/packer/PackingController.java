package com.github.nieldw.packer;

import com.github.nieldw.knapsack.KnapsackSolution;
import com.github.nieldw.knapsack.KnapsackSolver;
import com.github.nieldw.knapsack.constraints.KnapsackProblemConstraintViolationException;

import java.util.List;

import static java.util.stream.Collectors.toList;

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
     * @param parser  A {@link KnapsackProblemParser}
     * @param printer A {@link SolutionPrinter}
     * @param solver  A {@link KnapsackSolver}
     */
    public PackingController(KnapsackProblemParser parser, SolutionPrinter printer, KnapsackSolver solver) {
        this.parser = parser;
        this.printer = printer;
        this.solver = solver;
    }

    public String solve(String rawProblem) throws APIException {
        try {
            List<KnapsackSolution> knapsackSolutions = parser.parse(rawProblem).stream()
                    .map(problem -> solver.solve(problem))
                    .collect(toList());
            return printer.print(knapsackSolutions);
        } catch (IllegalArgumentException | KnapsackProblemConstraintViolationException e) {
            throw new APIException(e.getMessage(), e);
        }
    }
}
