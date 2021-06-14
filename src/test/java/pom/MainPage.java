package pom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Getter
    @FindBy(xpath = "//input[@id='headerSearch']")
    private WebElement searchField;

    @Getter
    @FindBy(xpath = "//input[@id='submit-searchmain']")
    private WebElement searchButton;

}
