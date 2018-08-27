package tst.springSample.annotationProcessors;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.core.type.AnnotationMetadata;
import tst.springSample.annotations.PostProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class PostProxyInvokerAnnotationContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConfigurableListableBeanFactory factory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext context = contextRefreshedEvent.getApplicationContext();
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            // пустое имя класса для бинов, созданных в java конфиге
            // при создании через @component имя класса есть
            String originalBeanClassName = beanDefinition.getBeanClassName();
            try {
              Class<?> originalBeanClass = Class.forName(originalBeanClassName);
                Method[] methods = originalBeanClass.getMethods();
                for (Method method : methods) {
                    if(method.isAnnotationPresent(PostProxy.class)) {
                        Object proxyBean = context.getBean(name);
                        Method proxyMethod = context.getBean(name).getClass().getMethod(method.getName(), method.getParameterTypes());
                        proxyMethod.invoke(proxyBean);
                    }
                }
            }
            catch( Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
