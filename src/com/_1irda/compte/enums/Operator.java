package com._1irda.compte.enums;

public enum Operator {

    ADDITION('+'),

    SUBTRACTION('-'),

    MULTIPLICATION('x'),

    DIVISION('/');

    private char symbol;

    Operator(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return this.symbol;
    }
}

