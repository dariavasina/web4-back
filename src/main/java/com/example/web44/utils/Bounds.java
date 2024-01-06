package com.example.web44.utils;

public enum Bounds {
    X_BOUNDS(-4, 4),
    Y_BOUNDS(-3, 5),
    R_BOUNDS(-4, 4);

    private final double left;
    private final double right;

    Bounds(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }
}
