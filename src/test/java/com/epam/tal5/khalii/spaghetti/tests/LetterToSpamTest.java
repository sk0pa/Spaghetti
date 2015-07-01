package com.epam.tal5.khalii.spaghetti.tests;

import com.epam.tal5.khalii.spaghetti.pages.LoginPage;
import com.epam.tal5.khalii.spaghetti.pages.MainPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LetterToSpamTest extends TestSettings {
    private LoginPage loginPage;
    private MainPage mainPage;

    @BeforeClass
    public void beforeClass() {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }

    @Test
    public void checkThatLetterInSpamFolder() {
        loginPage.navigateTo("http://gmail.com");
        loginPage.loginAction(USER1_LOGIN, USER1_PASSWORD);
        mainPage.composeMailBtnClick().sendMailToUser(USER2_LOGIN, SUBJECT, CONTENT);
        mainPage.addOrSwithUser(USER2_LOGIN, USER2_PASSWORD);
        mainPage.moveToSpam();
        mainPage.addOrSwithUser(USER1_LOGIN, USER1_PASSWORD);
        mainPage.composeMailBtnClick().sendMailToUser(USER2_LOGIN, SUBJECT, CONTENT);
        mainPage.addOrSwithUser(USER2_LOGIN, USER2_PASSWORD);
        mainPage.goToSpamFolder().assertThatLetterMoved(SUBJECT, CONTENT);
    }

    @AfterTest
    public void afterClass() {
        mainPage.logoutAction();
    }
}
