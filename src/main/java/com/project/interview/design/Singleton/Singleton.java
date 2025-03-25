package com.project.interview.design.Singleton;

/**
 * 饿汉式单例模式
 */
public class Singleton {

    private volatile static Singleton singleton;

    private Singleton(){}

    /**
     * 双锁机制
     * @return
     */
    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
