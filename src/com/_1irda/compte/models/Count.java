package com._1irda.compte.models;

import java.util.Scanner;

public class Count {

    private final Scanner sc;

    public Count() {
        sc = new Scanner(System.in);
    }

    private void home() {
        System.out.println("******************************");
        System.out.println("******* Compte est bon *******");
        System.out.println("******************************\n");
        System.out.println("(1) - Play");
        System.out.println("(2) - Game rules");
        System.out.println("(3) - Quit");

        int option = sc.nextInt();

        switch (option) {
            case 1 -> game();
            case 2 -> rules();
            case 3 -> sc.close();
        }
    }

    private void rules() {
        System.out.println("********** Rules **********");
    }

    private void game() {
        Level level = new Level();
        Draw draw = level.makeDraw();
        Solver solver = new Solver(draw.getToCompute());

        System.out.println(draw.toString());
        double start = System.currentTimeMillis();
        solver.solve(draw.getOps());
        System.out.println(solver.toString());
        System.out.printf("Solving time : %f seconds\n", (System.currentTimeMillis() - start) / 1000.0);
    }

    public void launch() {
        home();
    }
}
