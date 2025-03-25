package com.project.interview.design.Factory;

public class ShapeFactory {

    public Shape getShape(String type) {
        if (type.equalsIgnoreCase("circle")) {
            return new Circle();
        } else if (type.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        } else return new Square();
    }
}
