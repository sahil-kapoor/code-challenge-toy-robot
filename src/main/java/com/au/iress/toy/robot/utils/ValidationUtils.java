package com.au.iress.toy.robot.utils;

import java.util.function.BiPredicate;

public class ValidationUtils {

    public static BiPredicate<Integer,Integer> isPositionValid= (position,boardSide)-> position <= boardSide && position >=0 ;
}
