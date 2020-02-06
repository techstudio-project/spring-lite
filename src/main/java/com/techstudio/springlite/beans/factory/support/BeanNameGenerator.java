package com.techstudio.springlite.beans.factory.support;

import com.techstudio.springlite.beans.factory.config.BeanDefinition;

/**
 * @author lj
 * @date 2020/2/5
 */
public interface BeanNameGenerator {

    String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry);

}
