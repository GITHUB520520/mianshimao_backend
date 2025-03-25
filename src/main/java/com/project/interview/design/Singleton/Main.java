package com.project.interview.design.Singleton;

public class Main {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getSingleton();
        System.out.println(singleton);
        Test test = Test.getTest();
        System.out.println(test);
    }
}
