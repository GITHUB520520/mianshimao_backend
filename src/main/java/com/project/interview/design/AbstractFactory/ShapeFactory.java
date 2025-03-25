package com.project.interview.design.AbstractFactory;

import cn.hutool.core.util.StrUtil;

public class ShapeFactory extends AbstractFactory {
    @Override
    public Shape getShape(String shape) {
        if (StrUtil.isBlank(shape)) return null;
        else if (shape.equalsIgnoreCase("circle")) return new Circle();
        else if (shape.equalsIgnoreCase("rectangle")) return new Rectangle();
        return new Square();
    }

    @Override
    public Color getColor(String color) {
        return null;
    }
}
