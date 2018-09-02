package com.au.iress.toy.robot;

import com.au.iress.toy.robot.exception.DirectionException;
import com.au.iress.toy.robot.exception.PositionException;
import com.au.iress.toy.robot.exception.ToyException;
import com.au.iress.toy.robot.service.SquareBoard;
import com.au.iress.toy.robot.service.Robot;
import com.au.iress.toy.robot.utils.Constants;

import java.io.Console;
import java.util.Optional;

public class RobotApplication {

    public static void main(String[] args) {

        Optional<Console> console = Optional.ofNullable(System.console());

        if (!console.isPresent()) {
            System.err.println(Constants.SYSTEM_CONSOLE_ERROR);
            System.exit(1);
        }

        SquareBoard squareBoard = new SquareBoard(Constants.BOARD_SIDE_LENGTH);
        Robot robot = new Robot();
        Simulator simulator = new Simulator(squareBoard, robot);

        System.out.println(Constants.SYSTEM_TITLE);
        System.out.println(Constants.GAME_OPTIONS_DESC);
        System.out.println(Constants.GAME_VALID_OPTIONS_NORTH +System.lineSeparator()+
                Constants.GAME_VALID_OPTIONS_SOUTH+
                System.lineSeparator()+Constants.GAME_VALID_OPTIONS_EAST+System.lineSeparator()+
                Constants.GAME_VALID_OPTIONS_WEST+System.lineSeparator()+
                "Valid system commands: "+Constants.GAME_VALID_COMMANDS+System.lineSeparator()+
                "Valid movements: "+Constants.GAME_VALID_MOVEMENTS);
        System.out.println(Constants.SYSTEM_CONSOLE_INPUT_COMMAND);

        boolean isGameRunning = true;
        while (isGameRunning) {
            String inputString = console.get().readLine(": ");
            if ("EXIT".equals(inputString)) {
                isGameRunning = false;
            } else {
                try {
                    String outputVal = Optional.ofNullable(simulator.eval(inputString)).orElse("");
                    if(!outputVal.isEmpty())
                        System.out.println(outputVal);
                } catch (ToyException|DirectionException|PositionException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}