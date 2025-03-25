package com.project.interview.design.Factory;

public class Main {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.getShape("circle");
        shape.draw();
        shape = shapeFactory.getShape("rectangle");
        shape.draw();
        shape = shapeFactory.getShape("square");
        shape.draw();
    }
}
