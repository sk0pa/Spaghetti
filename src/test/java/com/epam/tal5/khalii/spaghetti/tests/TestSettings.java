package com.epam.tal5.khalii.spaghetti.tests;

import com.epam.tal5.khalii.spaghetti.driver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestSettings {
    public WebDriver driver;

    public static final String SUBJECT = "letter from " + "testusermindmap@gmail.com";
    public static final String CONTENT = "Content of letter from " + "testusermindmap@gmail.com";
    public static final String USER1_LOGIN = "testusermindmap@gmail.com";
    public static final String USER1_PASSWORD = "11mInd&22^Map46MAP36";
    public static final String USER2_LOGIN = "Webdriver42@gmail.com";
    public static final String USER2_PASSWORD = "webdriver123";

    @BeforeTest
    public void beforeSuite()
    {
//        Configuration.setUpConfiguration();
        String browser = "chrome";
        WebDriverFactory driverFactory = new WebDriverFactory();
        driver = driverFactory.getDriver(browser);
    }

    @AfterTest
    public void afterSuite()
    {
        driver.quit();
    }
}
