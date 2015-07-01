package com.epam.tal5.khalii.spaghetti.driver;

import org.openqa.selenium.WebDriver;


public abstract class AbstractDriverFactory {
    public abstract WebDriver getDriver(String parameter);
}
