package com.project.interview.design.Observer;

public class Observe2 implements Observe{

    public Subject subject;

    public Observe2(Subject subject){
        this.subject = subject;
        this.subject.register(this);
    }

    @Override
    public void update(String msg) {
        System.out.println("observe2 hahaha" + msg);
    }
}
