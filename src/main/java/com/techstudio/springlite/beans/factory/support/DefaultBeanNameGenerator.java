package com.techstudio.springlite.beans.factory.support;

import com.techstudio.springlite.beans.factory.config.BeanDefinition;

/**
 * @author lj
 * @date 2020/2/5
 */
public class DefaultBeanNameGenerator implements BeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        return definition.getBeanClassName();
    }
}
