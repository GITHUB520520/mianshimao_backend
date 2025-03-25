package com.project.interview.design.Observer;

import java.util.ArrayList;
import java.util.List;

public class Task implements Subject{

    private List<Observe> ans = new ArrayList<>();

    private String message;

    @Override
    public void register(Observe observe) {
        ans.add(observe);
    }

    @Override
    public void remove(Observe observe) {
        int i = ans.indexOf(observe);
        if(i >= 0) ans.remove(i);
    }

    public void setMessage(String message){
        this.message = message;
        notifyObserve();
    }

    @Override
    public void notifyObserve() {
        for(Observe o : ans) o.update(message);
    }
}
