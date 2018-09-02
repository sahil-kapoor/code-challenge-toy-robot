package com.au.iress.toy.robot;

import com.au.iress.toy.robot.exception.DirectionException;
import com.au.iress.toy.robot.exception.PositionException;
import com.au.iress.toy.robot.exception.ToyException;
import com.au.iress.toy.robot.service.*;
import com.au.iress.toy.robot.utils.Constants;

public class Simulator {

    SquareBoard squareBoard;
    Robot robot;

    public Simulator(SquareBoard squareBoard, Robot robot) {
        this.squareBoard = squareBoard;
        this.robot = robot;
    }

    public boolean placeToyRobot(Position position) throws ToyException,DirectionException,PositionException {

        if (null == squareBoard)
            throw new PositionException(Constants.BOARD_INVALID_BOARD);

        if (null == position)
            throw new PositionException(Constants.BOARD_INVALID_POSITION);

        if (null == position.getDirection())
            throw new DirectionException(Constants.BOARD_INVALID_DIRECTION);

        // validate the position
        if (!squareBoard.isValidPosition(position))
            return false;

        // if position is valid then assign values to fields
        robot.setPosition(position);
        return true;
    }

    public String eval(String inputString) throws ToyException,DirectionException,PositionException {
        String[] args = inputString.split(" ");
        Command command;
        try {
            command = Command.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            throw new ToyException(Constants.BOARD_INVALID_COMMAND);
        }
        if (command == Command.PLACE && args.length < 2) {
            throw new ToyException(Constants.BOARD_INVALID_COMMAND);
        }

        // validate PLACE params
        String[] params;
        int x = 0;
        int y = 0;
        Direction commandDirection = null;
        if (command == Command.PLACE) {
            params = args[1].split(",");
            try {
                x = Integer.parseInt(params[0]);
                y = Integer.parseInt(params[1]);
                commandDirection = Direction.valueOf(params[2]);
            } catch (Exception e) {
                throw new ToyException(Constants.BOARD_INVALID_COMMAND);
            }
        }

        String output="";

        switch (command) {
            case PLACE:
                placeToyRobot(new Position(x, y, commandDirection));
                break;
            case MOVE:
                Position newPosition = robot.getPosition().getNextPosition();
                if (!squareBoard.isValidPosition(newPosition)){}

                else
                    robot.move(newPosition);
                break;
            case LEFT:
                robot.rotateLeft();
                break;
            case RIGHT:
                    robot.rotateRight();
                break;
            case REPORT:
                output = report();
                break;
            default:
                throw new ToyException(Constants.BOARD_INVALID_COMMAND);
        }
        return output;
    }

    public String report() {
        if (robot.getPosition() == null){
            return "No valid PLACE command issued.  REPORT ignored.";
        }
        return robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getPosition().getDirection().toString();
    }
}
