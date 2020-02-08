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
    }

}
