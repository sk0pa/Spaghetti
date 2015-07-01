package com.epam.tal5.khalii.spaghetti.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class ThemesPage extends BasePage {
    private static final String THEMES_LIST_XPATH = "//tbody/tr[2]/td/div[@id]/div/div/div/div";
    private static final String THEME_CONFIRMATION_XPATH = "//div[@aria-live='assertive' and @role='alert' and @aria-atomic='true']/div/div[2]";

    @FindBy(xpath = THEMES_LIST_XPATH)
    private List<WebElement> themesList;

    @FindBy(xpath = THEME_CONFIRMATION_XPATH)
    private WebElement themesConfirmation;

    public ThemesPage(WebDriver driver) {
        super(driver);
    }

    public MainPage chooseRandomTheme() {
        PageHelper.waitForElementPresent(driver, THEMES_LIST_XPATH);

        Random random = new Random();
        int index = random.nextInt(themesList.size());
        themesList.get(index).click();
        PageHelper.waitForElementPresent(driver, THEME_CONFIRMATION_XPATH);

        return new MainPage(driver);
    }
}
