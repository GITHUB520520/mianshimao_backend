package com.project.interview.design.Builder;

import java.util.ArrayList;
import java.util.List;

public class Meal {

    private List<Item> list = new ArrayList<>();

    public void add(Item item) {
        list.add(item);
    }

    public float getTotal() {
        float sum = 0;
        for (Item item : list) {
            sum += item.price();
        }
        return sum;
    }

    public void show() {
        for (Item item : list) {
            System.out.println(item.name());
            System.out.println(item.price());
            System.out.println(item.packing());
        }
    }
}
