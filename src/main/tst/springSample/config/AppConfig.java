package tst.springSample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import tst.springSample.logic.Speach;
import tst.springSample.logic.SpeachImpl;
import tst.springSample.logic.Speaker;

@Configuration
@ComponentScan("tst.springSample")
public class AppConfig {

    @Bean
    public Speaker speaker() {
        return new Speaker() {
            @Override
            protected Speach getSpeach() {
                return speach();
            }
        };
    }

    @Bean
    @Scope("periodical")
    public Speach speach() {
        return new SpeachImpl();
    }
}
