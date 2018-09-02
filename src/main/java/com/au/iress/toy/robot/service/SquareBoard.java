package com.au.iress.toy.robot.service;

import com.au.iress.toy.robot.utils.ValidationUtils;

public class SquareBoard {

    private int sideLength;

    public SquareBoard(int sideLength) {
        this.sideLength = sideLength;
    }

    public boolean isValidPosition(Position position) {
        return  ValidationUtils.isPositionValid.test(position.getX(),this.sideLength) &&
                ValidationUtils.isPositionValid.test(position.getY(),this.sideLength);
    }

}
