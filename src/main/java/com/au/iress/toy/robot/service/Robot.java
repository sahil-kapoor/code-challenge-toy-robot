package com.au.iress.toy.robot.service;

import com.au.iress.toy.robot.exception.DirectionException;
import com.au.iress.toy.robot.exception.PositionException;

public class Robot {

    private Position position;

    public Robot() {
    }

    public Robot(Position position) {
        this.position = position;
    }

    public boolean setPosition(Position position) {
        if (null == position)
            return false;

        this.position = position;
        return true;
    }

    public boolean move() throws DirectionException,PositionException {
        return move(position.getNextPosition());
    }

    /**
     * Moves the robot one unit forward in the direction it is currently facing
     *
     * @return true if moved successfully
     */
    public boolean move(Position newPosition) {
        if (newPosition == null)
            return false;

        // change position
        this.position = newPosition;
        return true;
    }

    public Position getPosition() {
        return this.position;
    }

    /**
     * Rotates the robot 90 degrees LEFT
     *
     * @return true if rotated successfully
     */
    public boolean rotateLeft() {
        if (this.position.direction == null){
            System.out.println("No valid PLACE command issued.  LEFT ignored.");
            return false;
        }
        this.position.direction = this.position.direction.leftDirection();
        return true;
    }

    /**
     * Rotates the robot 90 degrees RIGHT
     *
     * @return true if rotated successfully
     */
    public boolean rotateRight() {
        if (this.position.direction == null){
            System.out.println("No valid PLACE command issued.  RIGHT ignored.");
            return false;
        }


        this.position.direction = this.position.direction.rightDirection();
        return true;
    }

}
