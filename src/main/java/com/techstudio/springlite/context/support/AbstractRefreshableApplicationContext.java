package com.techstudio.springlite.context.support;

import com.techstudio.springlite.beans.BeansException;
import com.techstudio.springlite.beans.factory.support.DefaultListableBeanFactory;
import com.techstudio.springlite.context.ApplicationContext;

/**
 * @author lj
 * @date 2020/2/8
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private String[] configLocations;

    private DefaultListableBeanFactory beanFactory;

    public AbstractRefreshableApplicationContext() {
    }

    public AbstractRefreshableApplicationContext(ApplicationContext parent) {
        super(parent);
    }

    @Override
    public DefaultListableBeanFactory getBeanFactory() {
        if (this.beanFactory == null) {
            throw new IllegalStateException("BeanFactory not initialized or already closed - " +
                    "call 'refresh' before accessing beans via the ApplicationContext");
        }
        return this.beanFactory;
    }

    @Override
    protected void refreshBeanFactory() {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    protected DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory(getInternalParentBeanFactory());
    }

    public void setConfigLocations(String... locations) {
        if (locations != null) {
            this.configLocations = new String[locations.length];
            for (int i = 0; i < locations.length; i++) {
                this.configLocations[i] = locations[i].trim();
            }
        }
        else {
            this.configLocations = null;
        }
    }

    public String[] getConfigLocations() {
        return configLocations;
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException;
}
