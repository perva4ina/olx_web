package com.gmail.perva4ina.config;

import com.gmail.perva4ina.pom.MainPage;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageConfiguration {

    @Bean
    public MainPage mainPage(WebDriver driver) {
        return new MainPage(driver);
    }
}
