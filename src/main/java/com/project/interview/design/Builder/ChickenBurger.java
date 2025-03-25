package com.project.interview.design.Builder;

public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "chicken burger";
    }

    @Override
    public float price() {
        return 15.5f;
    }
}
