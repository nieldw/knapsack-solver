package com.github.nieldw.packer;

import com.github.nieldw.knapsack.Item;
import com.github.nieldw.knapsack.KnapsackProblem;
import com.github.nieldw.knapsack.KnapsackProblemConstraintSupport;
import com.github.nieldw.knapsack.constraints.Constraint;
import com.github.nieldw.knapsack.constraints.KnapsackProblemConstraintViolationException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A {@link KnapsackProblemParser} that parses problems separated by newlines.
 */
public class MultiLineKnapsackProblemParser extends KnapsackProblemConstraintSupport implements KnapsackProblemParser {

    /**
     * Constructs a new {@link MultiLineKnapsackProblemParser} with no {@link Constraint}s on {@link KnapsackProblem}s.
     */
    MultiLineKnapsackProblemParser() {
        this(Collections.emptyList());
    }

    /**
     * Constructs a new {@link MultiLineKnapsackProblemParser}.
     *
     * @param constraints The {@link Constraint}s applied to problems produced by this object
     */
    MultiLineKnapsackProblemParser(List<Constraint> constraints) {
        super(constraints);
    }

    @Override
    public List<KnapsackProblem> parse(String inputString) throws APIException, KnapsackProblemConstraintViolationException {
        return Stream.of(inputString.trim().split("\n"))
                .map(line -> {
                    Pattern linePattern = Pattern.compile("^(?<weightLimit>\\d+) :(?<items>( \\((.*?)\\))*)"); // https://regex101.com/r/uuw4kF/2
                    Pattern itemPattern = Pattern.compile("^\\((?<index>\\d+),(?<weight>\\d+(\\.\\d+)*?),\\D(?<value>\\d+(\\.\\d+)*?)\\)"); // https://regex101.com/r/uuw4kF/3/
                    Matcher lineMatcher = linePattern.matcher(line);

                    if (lineMatcher.matches()) {
                        String weightLimit = lineMatcher.group("weightLimit");
                        String items = lineMatcher.group("items").trim();
                        List<Item> itemList = Stream.of(items.split(" "))
                                .map(itemString -> {
                                    Matcher itemMatcher = itemPattern.matcher(itemString);
                                    if (itemMatcher.matches()) {
                                        String index = itemMatcher.group("index");
                                        String weight = itemMatcher.group("weight");
                                        String value = itemMatcher.group("value");
                                        return new Item(Integer.parseInt(index), new BigDecimal(weight), new BigDecimal(value));
                                    }
                                    throw new APIException("Malformed problem input");
                                }).collect(Collectors.toList());
                        KnapsackProblem knapsackProblem = new KnapsackProblem(new BigDecimal(weightLimit), itemList);
                        super.checkConstraints(knapsackProblem);
                        return knapsackProblem;
                    }
                    throw new APIException("Malformed problem input");
                }).collect(Collectors.toList());
    }
}
