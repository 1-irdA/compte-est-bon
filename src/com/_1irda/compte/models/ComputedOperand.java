package com._1irda.compte.models;

import com._1irda.compte.enums.Operator;

public class ComputedOperand {

    private final Operand leftOpe;

    private final Operand rightOpe;

    private final Operator ope;

    private Operand result;

    public ComputedOperand(Operand left, Operand right, Operator operator) {
        leftOpe = left;
        rightOpe = right;
        ope = operator;
        compute();
    }

    private void compute() {
        switch (ope) {
            case ADDITION -> result = new Operand(leftOpe.getValue() + rightOpe.getValue());
            case SUBTRACTION -> result = new Operand(leftOpe.getValue() - rightOpe.getValue());
            case MULTIPLICATION -> result = new Operand(leftOpe.getValue() * rightOpe.getValue());
            case DIVISION -> {
                if (isCorrect()) {
                    result = new Operand(leftOpe.getValue() / rightOpe.getValue());
                } else {
                    result = new Operand(Integer.MAX_VALUE);
                }
            }
        }
    }

    private boolean isCorrect() {
        return rightOpe.getValue() != 0 && rightOpe.getValue() != 1 && leftOpe.getValue() % rightOpe.getValue() == 0;
    }

    public int getResultValue() {
        return result.getValue();
    }
}
