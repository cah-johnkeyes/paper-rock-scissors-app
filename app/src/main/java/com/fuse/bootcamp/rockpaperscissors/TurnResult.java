package com.fuse.bootcamp.rockpaperscissors;

public class TurnResult {
    public static enum Shape {
        ROCK,
        PAPER,
        SCISSORS
    }

    private Shape shape;

    public TurnResult(Shape shape) {
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }
}
