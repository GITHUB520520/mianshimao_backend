package com.project.interview.design.Builder;

public class VegBurger extends Burger {
    @Override
    public String name() {
        return "vegBuger";
    }

    @Override
    public float price() {
        return 12.0f;
    }
}
