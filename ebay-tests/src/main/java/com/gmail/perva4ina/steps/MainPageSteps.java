package com.gmail.perva4ina.steps;

import com.gmail.perva4ina.properties.WebUiProperties;
import com.thoughtworks.gauge.Step;
import lombok.AllArgsConstructor;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import com.gmail.perva4ina.pom.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class MainPageSteps {

    private final WebDriver driver;
    private final MainPage mainPage;
    private final WebUiProperties webUiProperties;

    @Step("Open home page")
    public void openMainPage() {
        driver.get(webUiProperties.getUrl());
        assertThat(driver.getTitle()).containsIgnoringCase("eBay");
    }

    @Step("Search for <query>")
    public void searchForItem(String query) {
        mainPage.getSearchField().sendKeys(query);
        mainPage.getSearchButton().click();
    }

    @Step("Verify items for <query> are found")
    public void verifyItemsFound(String query) {
        String label = mainPage.getItemsFoundLabel().getText();
        var softly = new SoftAssertions();
        softly.assertThat(label)
                .as("'.. results for ..' label should contain search query")
                .contains(query);
        softly.assertThat(extractFoundItemsCount(label))
                .as("0 items found on the label")
                .isGreaterThan(0);
        softly.assertThat(mainPage.getFoundItemsWidgets().size())
                .as("0 found items widgets are shown")
                .isGreaterThan(0);
        softly.assertAll();
    }

    @Step("Verify items for <query> are not found")
    public void verifyItemsNotFound(String query) {
        String label = mainPage.getItemsFoundLabel().getText();
        var softly = new SoftAssertions();
        softly.assertThat(label)
                .as("'.. results for ..' label should contain search query")
                .contains(query);
        softly.assertThat(extractFoundItemsCount(label))
                .as("0 items found on the label")
                .isEqualTo(0);
        softly.assertThat(mainPage.getFoundItemsWidgets().size())
                .as("0 found items widgets are shown")
                .isEqualTo(0);
        softly.assertAll();
    }

    private int extractFoundItemsCount(String label) {
        String itemsFund = label
                .substring(0, label.indexOf(" "))
                .replace(",", "");
        return Integer.parseInt(itemsFund);
    }
}