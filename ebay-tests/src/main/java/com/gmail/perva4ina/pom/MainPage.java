package com.gmail.perva4ina.pom;

import java.util.List;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Getter
    @FindBy(xpath = "//input[@id='gh-ac']")
    private WebElement searchField;

    @Getter
    @FindBy(xpath = "//input[@id='gh-btn']")
    private WebElement searchButton;

    @Getter
    @FindBy(xpath = "//h1[contains(.,' results for ')]")
    private WebElement itemsFoundLabel;

    @Getter
    @FindBy(xpath = "//ul[contains(@class, 'srp-results srp-list')]/li[contains(@class,'s-item')]")
    private List<WebElement> foundItemsWidgets;
}
