package com.epam.tal5.khalii.spaghetti.tests;


import com.epam.tal5.khalii.spaghetti.pages.LoginPage;
import com.epam.tal5.khalii.spaghetti.pages.MainPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DragAndDropTest extends TestSettings {
    private LoginPage loginPage;
    private MainPage mailPage;

    @BeforeTest
    public void beforeTest() {
        loginPage = new LoginPage(driver);
        mailPage = new MainPage(driver);
    }

    @Test
    public void checkThatLetterInStarred() {
        loginPage.navigateTo("http://gmail.com");
        loginPage.loginAction(USER1_LOGIN, USER1_PASSWORD);
        mailPage.composeMailBtnClick().sendMailToUser(USER1_LOGIN, SUBJECT, CONTENT);
        mailPage.addOrSwithUser(USER2_LOGIN, USER2_PASSWORD);
        mailPage.dragAndDropMessage();
        mailPage.goToStarredClick().assertThatLetterMoved(SUBJECT, CONTENT);
    }

    @AfterTest
    public void afterTest() {
        mailPage.logoutAction();
    }
}
