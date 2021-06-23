package com.gmail.perva4ina.config;

import com.gmail.perva4ina.driver.WebDriverConfiguration;
import com.gmail.perva4ina.pom.MainPage;
import com.gmail.perva4ina.properties.WebUiProperties;
import com.gmail.perva4ina.steps.MainPageSteps;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties({WebUiProperties.class})
@Import({PageConfiguration.class, WebDriverConfiguration.class})
public class StepsConfiguration {

    @Bean
    public MainPageSteps mainPageSteps(WebDriver driver, MainPage mainPage, WebUiProperties webUiProperties) {
        return new MainPageSteps(driver, mainPage, webUiProperties);
    }
}
