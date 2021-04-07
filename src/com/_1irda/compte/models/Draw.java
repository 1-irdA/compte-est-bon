package com._1irda.compte.models;

import java.util.ArrayList;
import java.util.Random;

public class Draw {
    
    private static final int MAX = 999;
    
    private static final int MIN = 101;

    private final ArrayList<Operand> ops;

    private final int toCompute;

    public Draw(ArrayList<Operand> operands) {
        toCompute = new Random().nextInt(MAX - MIN) + MIN;
        ops = operands;
    }

    public ArrayList<Operand> getOps() {
        return ops;
    }

    public int getToCompute() {
        return toCompute;
    }

    @Override
    public String toString() {
        StringBuilder toBuild = new StringBuilder();
        toBuild.append("To compute : ").append(toCompute).append("\nDraw : ");
        ops.forEach(o -> toBuild.append(o.getValue()).append(" "));
        return toBuild.append("\n").toString();
    }
}
