package tst.springSample.annotationProcessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import tst.springSample.annotations.DeprecatedClass;

@Component
public class DeprecationHandlerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] names = beanFactory.getBeanDefinitionNames();
        for (String name : names) {
            try {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
                // пустое имя класса для бинов, созданных в AppConfig.java
                // при создании через @component имя класса есть
                String className = beanDefinition.getBeanClassName();
                Class<?> beansClass = Class.forName(className);
                DeprecatedClass annotation = beansClass.getAnnotation(DeprecatedClass.class);
                if (annotation != null) {
                    Class<?> alternativeClass = annotation.newImpl();
                    beanDefinition.setBeanClassName(alternativeClass.getName());
                }
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
