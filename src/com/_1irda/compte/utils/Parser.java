package com._1irda.compte.utils;

import com._1irda.compte.enums.Operator;
import com._1irda.compte.models.ComputedOperand;
import com._1irda.compte.models.Operand;

import java.util.Arrays;
import java.util.function.Predicate;

public class Parser {

    private static final String SPLIT_NUMBERS_REGEX = "[-+/x=]";

    private static final String SPLIT_OPERATORS_REGEX = "\\d";

    public static ComputedOperand toComputedOperand(String toConvert) {
        toConvert = toConvert.replaceAll("\s", "");
        String[] operands = toParsableOperands(toConvert);
        String[] operators = toParsableOperators(toConvert);
        ComputedOperand computedOperand = new ComputedOperand(new Operand(Integer.parseInt(operands[0])),
                new Operand(Integer.parseInt(operands[1])),
                toOperator(operators[0].charAt(0)));
        return Integer.parseInt(operands[operands.length - 1]) == computedOperand.getResult().getValue()
                ? computedOperand
                : null;
    }

    public static int toOperationResult(String toConvert) {
        toConvert = toConvert.replaceAll("\s", "");
        String[] operands = toParsableOperands(toConvert);
        return Integer.parseInt(operands[operands.length - 1]);
    }

    private static String[] toParsableOperands(String toConvert) {
        return Arrays.stream(toConvert.split(SPLIT_NUMBERS_REGEX))
                .toArray(String[]::new);
    }

    private static String[] toParsableOperators(String toConvert) {
        return Arrays.stream(toConvert.split(SPLIT_OPERATORS_REGEX))
                .filter(Predicate.not(String::isBlank))
                .toArray(String[]::new);
    }

    private static Operator toOperator(char toConvert) {
        Operator correctOperator = null;
        switch (toConvert) {
            case '+' -> correctOperator = Operator.ADDITION;
            case 'x' -> correctOperator = Operator.MULTIPLICATION;
            case '-' -> correctOperator = Operator.SUBTRACTION;
            case '/' -> correctOperator = Operator.DIVISION;
        }
        return  correctOperator;
    }
}
