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

        TestBean1 testBean1 = (TestBean1) context.getBean("testBean1");
        testBean1.print("this is ioc test");
        // 还未实现
        // TestBean1 testBean11 = context.getBean(TestBean1.class);
        // testBean11.print("this is ioc test");
    }

}
