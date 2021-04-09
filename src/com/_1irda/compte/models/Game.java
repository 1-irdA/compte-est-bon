package com._1irda.compte.models;

import com._1irda.compte.utils.Parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com._1irda.compte.utils.Parser.toOperationResult;

public class Game {

    private static final String OPERATIONS_REGEX = "(\\s*\\d+\\s*[+\\-x\\/]\\s*\\d+\\s*=\\s*\\d+\\s*)";

    private final Scanner sc;

    private final String[] userOperations;

    private final Level level;

    private Draw draw;

    public Game() {
        sc = new Scanner(System.in);
        level = new Level();
        userOperations = new String[5];
    }

    private void home() {
        System.out.println("""
                ******************************
                ******* COMPTE EST BON *******
                ******************************
                """);
        menu();
    }

    private void menu() {
        System.out.print("""
                            (1) - Play
                            (2) - Game rules
                            (3) - Quit
                            """);
        menuChoices();
    }

    private void rules() {
        System.out.println("""
                            **************************** RULES ****************************
                            Enter operations line by line.
                            Available operators : x + - /
                            
                            Example :
                                15 + 54 = 69
                                5 + 3 = 8
                                54 x 8 = 432
                                
                            If an operation is incorrect, you loose.
                            Good luck !
                            **************************************************************
                            """);
        menu();
    }

    private void loose() {
        System.out.println("""
                ******************************************
                Input error or miscalculation, you loose !
                ******************************************
                """);
    }

    private void win() {
        System.out.println("""
                ****************************************
                Congratulations, you solve the problem !
                ****************************************
                """);
    }

    private void elapsedTime() {
        System.out.println("""
                *********************
                Time up !
                *********************
                """);
    }

    private void oneOperandRemaining() {
        System.out.println("""
                **********************************
                One remaining operand, well done !
                **********************************
                """);
    }

    private void menuChoices() {
        int option = -1;
        do {
            System.out.print("> ");
            if (sc.hasNextInt()) {
                option = sc.nextInt();
            } else {
                System.out.println("Invalid choice");
            }
            sc.nextLine();
        } while (isValidMenuOption(option));

        switch (option) {
            case 1 -> game();
            case 2 -> rules();
            case 3 -> sc.close();
        }
    }

    private void userInputOperations() {
        String operation = "";
        int nbOperations = 0;
        boolean isPlay;
        double start = System.currentTimeMillis();
        ComputedOperand computedUserTotal;

        do {
            System.out.println(draw);
            System.out.println("Your operations : ");
            printUserOperations();
            System.out.print("> ");

            isPlay = isTimeNotElapsed(start) && isEnoughOperands() && isNotWin(operation);
            if (isPlay) {
                operation = sc.nextLine();
                isPlay = isTimeNotElapsed(start) && isEnoughOperands() && isNotWin(operation);
            }
            if (isPlay && Pattern.matches(OPERATIONS_REGEX, operation)) {
                computedUserTotal = Parser.toComputedOperand(operation);
                isPlay = computedUserTotal != null && draw.isOperandsInDraw(computedUserTotal);
                if (isPlay) {
                    draw.update(computedUserTotal);
                    userOperations[nbOperations] = operation;
                    nbOperations++;
                } else {
                    loose();
                }
            } else if (!isNotWin(operation)) {
                win();
            } else if (!isTimeNotElapsed(start)) {
                elapsedTime();
            } else if (!isEnoughOperands()) {
                oneOperandRemaining();
            } else {
                System.out.println("Input error ! (ex : 15 + 54 = 69)");
            }
        } while (isPlay);
    }

    private boolean isTimeNotElapsed(double start) {
        return System.currentTimeMillis() - start <= level.getDuration();
    }

    private boolean isEnoughOperands() {
        return draw.getOperands().size() > 1;
    }

    private boolean isNotWin(String operation) {
        return operation.isBlank() ? operation.isBlank() : draw.getToCompute() != toOperationResult(operation);
    }

    private boolean isValidMenuOption(int toCheck) {
        return toCheck < 1 || toCheck > 3;
    }

    private void printUserOperations() {
        Arrays.stream(userOperations)
                .filter(item -> Optional.ofNullable(item).isPresent())
                .forEach(System.out::println);
    }

    private void game() {
        draw = level.makeDraw();
        Solver solver = new Solver(draw.getToCompute());
        ArrayList<Operand> copyDraw = (ArrayList<Operand>) draw.getOperands().clone();

        userInputOperations();
        double start = System.currentTimeMillis();
        solver.solve(copyDraw);
        System.out.println(solver);
        System.out.printf("Computer solving time : %f seconds\n", (System.currentTimeMillis() - start) / 1000.0);
    }

    public void launch() {
        home();
    }
}
