package com.techstudio.springlite;

import com.techstudio.springlite.context.ApplicationContext;
import com.techstudio.springlite.context.support.ClassPathXmlApplicationContext;

/**
 * @author lj
 * @date 2020/2/8
 */
public class IOCAppTest {

    public static void main(String[] args) {
        String location = "classpath:spring-context.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(location);

        TestBean1 testBean1 = context.getBean(TestBean1.class);
        testBean1.print("this is ioc test");

        TestBean2 testBean2 = context.getBean(TestBean2.class);
        testBean2.getTestBean1().print("这是属性注入的测试");

        TestBean2 testBean22 = context.getBean(TestBean2.class);
        testBean22.getTestBean11().print("这是构造器注入的测试");

    }

}
