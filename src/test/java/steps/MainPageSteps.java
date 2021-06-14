package steps;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.WebDriver;
import pom.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

public class MainPageSteps {

    private final WebDriver driver;
    private final MainPage mainPage;

    public MainPageSteps() {
        this.driver = Driver.webDriver;
        this.mainPage = new MainPage(this.driver);
    }

    @Step("Open home page")
    public void openMainPage() {
        String appUrl = System.getenv("APP_URL");
        Driver.webDriver.get(appUrl + "/");
        assertThat(Driver.webDriver.getTitle()).containsIgnoringCase("OLX");
    }
}