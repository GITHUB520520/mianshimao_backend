package com.project.interview.design.Observer;

public class Main {
    public static void main(String[] args) {
        Task task = new Task();
        Observe1  observe1 = new Observe1(task);
        Observe2 observe2 = new Observe2(task);
        task.setMessage("xiaoxi1");
        task.setMessage("xiaoxi2");
    }
}
