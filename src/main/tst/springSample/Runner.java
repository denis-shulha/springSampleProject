package tst.springSample;

import tst.springSample.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tst.springSample.logic.Speach;
import tst.springSample.logic.Speaker;

public class Runner {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
            for (int i = 0 ; i< 10; i++) {
                context.getBean(Speaker.class).speak();
                Thread.sleep(500);
            }
            context.getBean(Speach.class).content();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }


    }
}
