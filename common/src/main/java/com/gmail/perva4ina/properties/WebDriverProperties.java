package com.gmail.perva4ina.properties;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Value
@ConstructorBinding
@ConfigurationProperties(prefix = "web.driver")
public class WebDriverProperties {

    Name name;
    Mode mode;
    boolean headless;

    public enum Name {
        CHROME,
        FIREFOX
    }

    public enum Mode {
        LOCAL,
        BROWSERSTACK
    }
}
