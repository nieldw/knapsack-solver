package com.github.nieldw.packer;

import com.github.nieldw.knapsack.Item;
import com.github.nieldw.knapsack.KnapsackProblem;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultiLineKnapsackProblemParser implements KnapsackProblemParser {
    @Override
    public KnapsackProblem parse(String inputString) {
        Pattern linePattern = Pattern.compile("^(?<weightLimit>\\d+) :(?<items>( \\((.*?)\\))*)"); // https://regex101.com/r/uuw4kF/2
        Pattern itemPattern = Pattern.compile("^\\((?<index>\\d+),(?<weight>\\d+(\\.\\d+)*?),\\D(?<value>\\d+(\\.\\d+)*?)\\)");// https://regex101.com/r/uuw4kF/3/
        Matcher lineMatcher = linePattern.matcher(inputString);

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
            return new KnapsackProblem(new BigDecimal(weightLimit), itemList);
        }
        throw new APIException("Malformed problem input");
    }
}
