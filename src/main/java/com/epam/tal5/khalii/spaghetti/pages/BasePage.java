package com.epam.tal5.khalii.spaghetti.pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected final WebDriver driver;

    public WebDriver getDriver(){return driver;}

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public BasePage navigateTo(String url){
        driver.get(url);
        return this;
    }
}
