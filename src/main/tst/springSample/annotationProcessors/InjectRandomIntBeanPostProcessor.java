package tst.springSample.annotationProcessors;

import tst.springSample.annotations.InjectRandomInt;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Random;

@Component
public class InjectRandomIntBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        Arrays.stream(o.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(InjectRandomInt.class) != null)
                .peek(field -> field.setAccessible(true))
                .forEach(field -> {
                    Annotation annotation = field.getAnnotation(InjectRandomInt.class);
                    Random random = new Random();
                    int min = ((InjectRandomInt) annotation).min();
                    int max = ((InjectRandomInt) annotation).max();
                    ReflectionUtils.setField(field, o,min + random.nextInt(max - min));
                }
                );
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return o;
    }
}
