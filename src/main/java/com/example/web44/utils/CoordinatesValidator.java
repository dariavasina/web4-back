package com.example.web44.utils;

import com.example.web44.model.Coordinates;

public class CoordinatesValidator {
    public static boolean validate(Coordinates coordinates) {
        boolean xValid = validateCoordinate(coordinates.getX(), Bounds.X_BOUNDS);
        boolean yValid = validateCoordinate(coordinates.getY(), Bounds.Y_BOUNDS);
        boolean rValid = validateCoordinate(coordinates.getR(), Bounds.R_BOUNDS);

        return xValid && yValid && rValid;

    }

    public static boolean validateCoordinate(double coordinate, Bounds bounds) {
        return !(coordinate < bounds.getLeft()) && !(coordinate > bounds.getRight());
    }



}
