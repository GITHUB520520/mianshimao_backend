package com.project.interview.design.AbstractFactory;

public class Main {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.getShape("circle");
        shape.draw();
        ColorFactory colorFactory = new ColorFactory();
        Color color = colorFactory.getColor("red");
        color.getColor();
    }
}
