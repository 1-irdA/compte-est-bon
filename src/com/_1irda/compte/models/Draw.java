package com._1irda.compte.models;

import java.util.ArrayList;
import java.util.Random;

public class Draw {
    
    private static final int MAX = 999;
    
    private static final int MIN = 101;

    private final ArrayList<Operand> operands;

    private final int toCompute;

    public Draw(ArrayList<Operand> operands) {
        toCompute = new Random().nextInt(MAX - MIN) + MIN;
        this.operands = operands;
    }

    public ArrayList<Operand> getOperands() {
        return operands;
    }

    public int getToCompute() {
        return toCompute;
    }

    public void update(ComputedOperand toAdd) {
        removeOperand(toAdd.getLeft());
        removeOperand(toAdd.getRight());
        operands.add(toAdd.getResult());
    }

    private void removeOperand(Operand toRemove) {
        int operandsSize = operands.size();

        for (int i = 0; i < operands.size() && operandsSize == operands.size(); i++) {
            if (operands.get(i).getValue() == toRemove.getValue()) {
                operands.remove(i);
            }
        }
    }

    public boolean isOperandsInDraw(ComputedOperand toCheck) {
        boolean isLeftPresent = false;
        boolean isRightPresent = false;

        for (Operand op : operands) {
            if (op.getValue() == toCheck.getLeft().getValue()) {
                isLeftPresent = true;
            }
            if (op.getValue() == toCheck.getRight().getValue()) {
                isRightPresent = true;
            }
        }
        return isLeftPresent && isRightPresent;
    }

    @Override
    public String toString() {
        StringBuilder toBuild = new StringBuilder();
        toBuild.append("To compute : ").append(toCompute).append("\nDraw : ");
        operands.forEach(o -> toBuild.append(o.getValue()).append(" "));
        return toBuild.append("\n").toString();
    }
}
