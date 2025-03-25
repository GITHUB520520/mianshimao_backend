package com.project.interview.design.Observer;

public class Observe1 implements Observe{

    public Subject subject;

    public Observe1(Subject subject){
        this.subject = subject;
        this.subject.register(this);
    }


    @Override
    public void update(String msg) {
        System.out.println("observe1 hahaha" + msg);
    }
}
