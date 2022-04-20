package com._1irda.compte.models;

import com._1irda.compte.enums.Operator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

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

        ops.forEach(left -> {   

            ops.forEach(right -> {

                if (left != right) {

                    Arrays
                        .stream(Operator.values())
                        .forEach(operator -> {
                    
                        int temp = new ComputedOperand(left, right, operator)
                                        .compute()
                                        .getResult()
                                        .getValue();
                        tempOperations[level] = getOperation(left, right, operator, temp);
                        
                        /* temp is current better solution */
                        if (temp >= betterResult && temp <= toCompute) {
                            betterResult = temp;
                            finalOperations = tempOperations.clone();
                        } else if (temp < toCompute && ops.size() != 1) {                 
                            ArrayList<Operand> newOps = new ArrayList<>(ops);
                            newOps.remove(left);
                            newOps.remove(right);
                            newOps.add(new Operand(temp));
                            level++;
                            solve(newOps);
                            level--;
                            tempOperations[level] = null;
                        }
                    });
                } 
            });
        });
    }

    private String getOperation(Operand left, Operand right, Operator operator, int total) {
        return left.getValue() + " " + operator.getSymbol() + " " + right.getValue() + " = " + total;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Computer solution :\n");

        Arrays.stream(finalOperations)
            .filter(Objects::nonNull)
            .forEach(v -> builder.append(v).append("\n"));

        return builder.toString();
    }
}
