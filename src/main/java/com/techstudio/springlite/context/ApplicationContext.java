package com.techstudio.springlite.context;

import com.techstudio.springlite.beans.factory.ListableBeanFactory;
import com.techstudio.springlite.beans.factory.config.AutowireCapableBeanFactory;

/**
 * @author lj
 * @date 2020/2/5
 */
public interface ApplicationContext extends ListableBeanFactory {

    String getId();

    String getApplicationName();

    ApplicationContext getParent();

    AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException;

}
