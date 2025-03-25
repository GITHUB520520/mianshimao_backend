package com.project.interview.design.Builder;

public class Main {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();
        Meal meal = mealBuilder.prepareVegMeal();
        System.out.println(meal.getTotal());
        meal.show();
        meal = mealBuilder.prepareNoVegMeal();
        System.out.println(meal.getTotal());
        meal.show();
    }
}
