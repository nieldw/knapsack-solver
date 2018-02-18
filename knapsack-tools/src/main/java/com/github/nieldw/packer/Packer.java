package com.github.nieldw.packer;

import com.github.nieldw.knapsack.KnapsackSolver;
import com.github.nieldw.knapsack.ZeroOneKnapsackSolver;
import com.github.nieldw.knapsack.constraints.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * This packer reads a list of knapsack problems from a file a returns a string representing the solution to each.
 */
public class Packer {

    /**
     * Solve the knapsack problem on each line of the given file.
     *
     * @param absoluteFilePath The absolute path the the input file
     * @return The solution of each line
     * @throws APIException If any problem parameters are invalid
     */
    public static String pack(String absoluteFilePath) throws APIException {
        File file = new File(absoluteFilePath);
        if (!file.exists()) {
            throw new APIException("File not found: " + absoluteFilePath);
        }

        PackingController controller = getPackingController();
        String rawProblem = getFileContents(file);

        try {
            return controller.solve(rawProblem);
        } catch (KnapsackProblemConstraintViolationException e) {
            throw new APIException(e.getMessage(), e);
        }
    }

    @NotNull
    private static String getFileContents(File file) throws APIException {
        String rawProblem;
        try {
            rawProblem = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new APIException("Could not read file", e);
        }
        return rawProblem;
    }

    @NotNull
    private static PackingController getPackingController() {
        List<Constraint> constraints = asList(
                new KnapsackWeightConstraint(new BigDecimal("100")),
                new ItemListSizeConstraint(15),
                new ItemValueConstraint(new BigDecimal("100")),
                new ItemWeightConstraint(new BigDecimal("100")));

        KnapsackProblemParser parser = new MultiLineKnapsackProblemParser(constraints);
        SolutionPrinter printer = new IndexListSolutionPrinter();
        KnapsackSolver solver = new ZeroOneKnapsackSolver();

        return new PackingController(parser, printer, solver);
    }
}
