package com._1irda.compte.models;

import com._1irda.compte.enums.Operator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Solver {

    private final String[] tempOperations;

    private String[] finalOperations;

    private int betterResult;

    private final int toCompute;

    private int level;

    public Solver(int toGet) {
        tempOperations = new String[5];
        toCompute = toGet;
        betterResult = Integer.MIN_VALUE;
        level = 0;
    }

    public void solve(ArrayList<Operand> ops) {
        /* throughout each operand if there are different */
        ops.forEach(leftOpe -> ops.stream().filter(rightOpe -> rightOpe != leftOpe).forEach(rightOpe -> {
                /* throughout each operator */
                Arrays.stream(Operator.values()).forEach(operator -> {
                    ComputedOperand computedOperand = new ComputedOperand(leftOpe, rightOpe, operator);
                    int temp = computedOperand.getResultValue();
                    if (temp >= betterResult && temp <= toCompute) {
                        tempOperations[level] = getOperation(leftOpe, rightOpe, operator, temp);
                        betterResult = temp;
                        finalOperations = tempOperations.clone();
                    } else if (temp < toCompute && ops.size() != 1) {
                        /* remove used operands and add result operand */
                        ArrayList<Operand> newOps = (ArrayList<Operand>) ops.clone();
                        newOps.remove(leftOpe);
                        newOps.remove(rightOpe);
                        newOps.add(new Operand(temp));
                        tempOperations[level] = getOperation(leftOpe, rightOpe, operator, temp);
                        level += 1;
                        solve(newOps);
                        level -= 1;
                        tempOperations[level] = null;
                    }
                });
        }));
    }

    private String getOperation(Operand leftOpe, Operand rightOpe, Operator operator, int total) {
        return leftOpe.getValue() + " " + operator.getSymbol() + " " + rightOpe.getValue() + " = " + total;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(finalOperations).filter(Objects::nonNull).forEach(v -> builder.append(v).append("\n"));
        return builder.toString();
    }
}
