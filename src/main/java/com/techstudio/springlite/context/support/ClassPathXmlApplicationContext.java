package com.techstudio.springlite.context.support;

import com.techstudio.springlite.context.ApplicationContext;

/**
 * @author lj
 * @date 2020/2/5
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    public ClassPathXmlApplicationContext() {
    }

    public ClassPathXmlApplicationContext(ApplicationContext parent) {
        super(parent);
    }

    public ClassPathXmlApplicationContext(String configLocation) {
        this(new String[]{configLocation}, true, null);
    }

    public ClassPathXmlApplicationContext(String... configLocations)  {
        this(configLocations, true, null);
    }

    public ClassPathXmlApplicationContext(String[] configLocations, boolean refresh, ApplicationContext parent) {
        super(parent);
        setConfigLocations(configLocations);
        if(refresh){
            refresh();
        }
    }

}
