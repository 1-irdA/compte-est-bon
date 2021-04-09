package com._1irda.compte.models;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class Level {

    private static final int NB_OPERANDS = 6;

    private double duration;

    public Level() {
        duration = 60.0 * 1000;
    }

    public Draw makeDraw() {
        ArrayList<Integer> available = new ArrayList<>();
        ArrayList<Operand> usableInDraw = new ArrayList<>();
        Random r = new Random();

        IntStream.range(0, 2).forEach(i -> IntStream.rangeClosed(1, 10).forEach(available::add));
        IntStream.iterate(25, i -> i + 25).limit(4).forEach(available::add);
        IntStream.range(0, NB_OPERANDS).forEach(i -> {
            int index = r.nextInt(available.size());
            usableInDraw.add(new Operand(available.get(index)));
            available.remove(index);
        });

        return new Draw(usableInDraw);
    }

    public double getDuration() {
        return duration;
    }
}
