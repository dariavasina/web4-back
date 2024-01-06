package com.example.web44.utils;

import com.example.web44.model.Coordinates;

public class AreaChecker {
    public static boolean isHit(Coordinates coordinates) {
        double x = coordinates.getX();
        double y = coordinates.getY();
        double r = coordinates.getR();

        boolean checkCircle = (x <= 0 && y <= 0 && x * x + y * y <= r * r);
        boolean checkRectangle = (x <= 0 && y >= 0 && x >= -r && y <= r);
        boolean checkTriangle = (x >= 0 && y >= 0 && y <= (-2) * x + r);

        return checkCircle || checkRectangle || checkTriangle;
    }
}
