package com.github.nieldw.packer;

import java.io.File;

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

        return "";
    }
}
