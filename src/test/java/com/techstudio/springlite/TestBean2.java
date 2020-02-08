package com.techstudio.springlite;

/**
 * @author lj
 * @date 2020/2/8
 */
public class TestBean2 {

    private String param = "bean2";

    private TestBean1 testBean1;

    private final TestBean1 testBean11;

    public TestBean2(TestBean1 testBean11) {
        this.testBean11 = testBean11;
    }

    public TestBean1 getTestBean1() {
        return testBean1;
    }

    public void setTestBean1(TestBean1 testBean1) {
        this.testBean1 = testBean1;
    }

    public TestBean1 getTestBean11() {
        return testBean11;
    }
}
