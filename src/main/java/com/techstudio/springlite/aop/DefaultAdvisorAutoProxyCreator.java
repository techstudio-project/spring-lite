package com.techstudio.springlite.aop;

import com.techstudio.springlite.util.AopUtils;
import org.aopalliance.aop.Advice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lj
 * @date 2020/2/16
 */
public class DefaultAdvisorAutoProxyCreator extends AbstractAutoProxyCreator {

    private String[] cachedAdvisorBeanNames;

    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName) {
        List<Advisor> advisors = findEligibleAdvisors(beanClass, beanName);
        if (advisors.isEmpty()) {
            return DO_NOT_PROXY;
        }
        return advisors.toArray();
    }

    private List<Advisor> findEligibleAdvisors(Class<?> beanClass, String beanName) {
        List<Advisor> candidateAdvisors = findCandidateAdvisors();
        List<Advisor> eligibleAdvisors = findAdvisorsThatCanApply(candidateAdvisors, beanClass, beanName);
        if (!eligibleAdvisors.isEmpty()) {
            // 排序，这里就省略了
        }
        return eligibleAdvisors;
    }

    private List<Advisor> findAdvisorsThatCanApply(List<Advisor> candidateAdvisors, Class<?> beanClass, String beanName) {
        if (candidateAdvisors.isEmpty()) {
            return candidateAdvisors;
        }
        List<Advisor> eligibleAdvisors = new ArrayList<>();
        for (Advisor candidate : candidateAdvisors) {
            if (AopUtils.canApply(candidate, beanClass)) {
                eligibleAdvisors.add(candidate);
            }
        }
        return eligibleAdvisors;
    }

    private List<Advisor> findCandidateAdvisors() {
        String[] advisorNames = this.cachedAdvisorBeanNames;
        if (advisorNames == null) {
            // 在容器中查找所有的advisor
            advisorNames = getBeanFactory().getBeanNamesForType(Advisor.class);
            this.cachedAdvisorBeanNames = advisorNames;
        }
        if (advisorNames.length == 0) {
            return new ArrayList<>();
        }

        List<Advisor> advisors = new ArrayList<>();
        for (String name : advisorNames) {
            advisors.add(getBeanFactory().getBean(name, Advisor.class));
        }
        return advisors;
    }
}
