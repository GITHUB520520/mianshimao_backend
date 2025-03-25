package com.project.interview.design.Singleton;

/**
 * 明确实现 lazy loading 效果时
 */
public class Test {

    private static Test test;

    private Test() {
    }

    private static class TestHolder {
        public static final Test test = new Test();
    }

    public static final Test getTest() {
        return TestHolder.test;
    }

}
