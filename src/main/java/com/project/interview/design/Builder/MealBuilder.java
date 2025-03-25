package com.project.interview.design.Builder;

public class MealBuilder {

    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.add(new VegBurger());
        meal.add(new Coke());
        return meal;
    }

    public Meal prepareNoVegMeal() {
        Meal meal = new Meal();
        meal.add(new ChickenBurger());
        meal.add(new Pepsi());
        return meal;
    }
}
