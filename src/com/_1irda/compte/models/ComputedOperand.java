package com._1irda.compte.models;

import com._1irda.compte.enums.Operator;

public class ComputedOperand {

    private final Operand leftOpe;

    private final Operand rightOpe;

    private final Operator operator;

    private Operand result;

    public ComputedOperand(Operand left, Operand right, Operator arithmeticOperator) {
        leftOpe = left;
        rightOpe = right;
        operator = arithmeticOperator;
        compute();
    }

    private void compute() {
        switch (operator) {
            case ADDITION -> result = new Operand(leftOpe.getValue() + rightOpe.getValue());
            case SUBTRACTION -> result = new Operand(leftOpe.getValue() - rightOpe.getValue());
            case MULTIPLICATION -> {
                if (isCorrectMultiplication()) {
                    result = new Operand(leftOpe.getValue() * rightOpe.getValue());
                } else {
                    result = new Operand(Integer.MAX_VALUE);
                }
            }
            case DIVISION -> {
                if (isCorrectDivision()) {
                    result = new Operand(leftOpe.getValue() / rightOpe.getValue());
                } else {
                    result = new Operand(Integer.MAX_VALUE);
                }
            }
        }
    }

    private boolean isCorrectMultiplication() {
        return leftOpe.getValue() != 1 && rightOpe.getValue() != 1;
    }

    private boolean isCorrectDivision() {
        return rightOpe.getValue() != 0 && rightOpe.getValue() != 1 && leftOpe.getValue() % rightOpe.getValue() == 0;
    }

    public Operand getLeftOpe() {
        return leftOpe;
    }

    public Operand getRightOpe() {
        return rightOpe;
    }

    public Operand getResult() {
        return result;
    }
}
