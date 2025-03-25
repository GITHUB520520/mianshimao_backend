package com.project.interview.design.AbstractFactory;

import cn.hutool.core.util.StrUtil;

public class ColorFactory extends AbstractFactory {
    @Override
    public Shape getShape(String shape) {
        return null;
    }

    @Override
    public Color getColor(String color) {
        if (StrUtil.isBlank(color)) return null;
        else if (color.equalsIgnoreCase("red")) return new Red();
        else if (color.equalsIgnoreCase("green")) return new Green();
        return new Blue();
    }
}
