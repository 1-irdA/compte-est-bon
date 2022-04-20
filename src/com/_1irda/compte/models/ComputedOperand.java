package com._1irda.compte.models;

import com._1irda.compte.enums.Operator;

public class ComputedOperand {

    private final Operand left;

    private final Operand right;

    private final Operator operator;

    private Operand result;

    public ComputedOperand(Operand l, Operand r, Operator ope) {
        left = l;
        right = r;
        operator = ope;
    }

    private ComputedOperand compute() {
        switch (operator) {
            case ADDITION -> result = new Operand(left.getValue() + right.getValue());
            case SUBTRACTION -> result = new Operand(left.getValue() - right.getValue());
            case MULTIPLICATION -> {
                if (isCorrectMultiplication()) {
                    result = new Operand(left.getValue() * right.getValue());
                } else {
                    result = new Operand(Integer.MAX_VALUE);
                }
            }
            case DIVISION -> {
                if (isCorrectDivision()) {
                    result = new Operand(left.getValue() / right.getValue());
                } else {
                    result = new Operand(Integer.MAX_VALUE);
                }
            }
        }
        return this;
    }

    private boolean isCorrectMultiplication() {
        return left.getValue() != 1 && right.getValue() != 1;
    }

    private boolean isCorrectDivision() {
        return right.getValue() != 0 && right.getValue() != 1 && left.getValue() % right.getValue() == 0;
    }

    public Operand getleft() {
        return left;
    }

    public Operand getright() {
        return right;
    }

    public Operand getResult() {
        return result;
    }
}
