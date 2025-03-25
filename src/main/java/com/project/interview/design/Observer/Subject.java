package com.project.interview.design.Observer;

public interface Subject {

    public void register(Observe observe);

    public void remove(Observe observe);

    public void notifyObserve();
}
