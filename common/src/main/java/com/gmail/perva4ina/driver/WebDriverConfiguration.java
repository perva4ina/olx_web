package com.gmail.perva4ina.driver;


import java.util.concurrent.TimeUnit;

import com.gmail.perva4ina.properties.WebDriverProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties({WebDriverProperties.class})
public class WebDriverConfiguration {

    @Bean(destroyMethod = "quit")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "web.driver.name", havingValue = "chrome")
    public WebDriver chromeDriver(ChromeOptions options, WebDriverProperties webDriverProperties) {
        log.info("Creating Chrome driver");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        setupDriver(driver, webDriverProperties);
        return driver;
    }

    @Bean(destroyMethod = "quit")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "web.driver.name", havingValue = "firefox")
    public WebDriver firefoxDriver(FirefoxOptions options, WebDriverProperties webDriverProperties) {
        log.info("Creating Firefox driver");
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver(options);
        setupDriver(driver, webDriverProperties);
        return driver;
    }

    @Bean
    @ConditionalOnMissingBean
    public ChromeOptions chromeOptions(WebDriverProperties webDriverProperties) {
        var options = new ChromeOptions();
        options.addArguments("--start-maximized",
                             "--disable-notifications",
                             "--mute-audio");

        if (webDriverProperties.isHeadless()) {
            options.addArguments("--headless",
                                 "--disable-gpu");
        }
        return options;
    }

    @Bean
    @ConditionalOnMissingBean
    public FirefoxOptions firefoxOptions(WebDriverProperties webDriverProperties) {
        var profile = new FirefoxProfile();
        profile.setPreference("network.cookie.cookieBehavior", 0);
        var options = new FirefoxOptions();
        options.setProfile(profile);
        options.setLogLevel(FirefoxDriverLogLevel.INFO);
        options.setHeadless(webDriverProperties.isHeadless());
        return options;
    }

    private void setupDriver(WebDriver driver, WebDriverProperties webDriverProperties) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(webDriverProperties.getTimeout(), TimeUnit.MILLISECONDS);
    }
}
