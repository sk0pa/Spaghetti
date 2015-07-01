package com.epam.tal5.khalii.spaghetti.tests;

import com.epam.tal5.khalii.spaghetti.pages.LoginPage;
import com.epam.tal5.khalii.spaghetti.pages.MainPage;
import com.epam.tal5.khalii.spaghetti.pages.ThemesPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ThemesTest extends TestSettings {
    private LoginPage loginPage;
    private MainPage mailPage;
    private ThemesPage themesPage;

    @BeforeClass
    public void beforeClass() {
        loginPage = new LoginPage(driver);
        mailPage = new MainPage(driver);
        themesPage = new ThemesPage(driver);
    }

    @Test
    public void checkThatThemeChanged() {
        loginPage.navigateTo("http://gmail.com");
        loginPage.loginAction(TestSettings.USER1_LOGIN, TestSettings.USER1_PASSWORD);
        mailPage.openThemeSettings().chooseRandomTheme();
    }

    @AfterTest
    public void afterTest() {
        mailPage.logoutAction();
    }
}
