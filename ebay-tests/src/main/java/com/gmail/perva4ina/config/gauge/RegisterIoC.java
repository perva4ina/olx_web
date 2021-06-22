package com.gmail.perva4ina.config.gauge;

import com.gmail.perva4ina.config.StepsConfiguration;
import com.thoughtworks.gauge.ClassInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
public class RegisterIoC implements ClassInitializer {

    private static ConfigurableApplicationContext context;

    static {
        log.info("Context starting...");
        context = new SpringApplicationBuilder(StepsConfiguration.class)
                .web(WebApplicationType.NONE)
                .run();
        log.info("Context started");
    }

    @Override
    public Object initialize(Class<?> clazz) throws Exception {
        String[] beanNames = context.getBeanNamesForType(clazz);
        if (beanNames.length == 0) {
            throw new NoSuchBeanDefinitionException(clazz.getName());
        }
        String s = beanNames[0];
        return clazz.cast(context.getBean(s));
    }
}
