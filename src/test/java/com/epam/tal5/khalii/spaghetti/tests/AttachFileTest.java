package com.epam.tal5.khalii.spaghetti.tests;

import com.epam.tal5.khalii.spaghetti.pages.LoginPage;
import com.epam.tal5.khalii.spaghetti.pages.MainPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AttachFileTest extends TestSettings{
    private static final String filePath = System.getProperty("user.dir") + "src\\main\\resources\\testfile";
    private LoginPage loginPage;
    private MainPage mailPage;

    @BeforeTest
    public void beforeTest() {
        loginPage = new LoginPage(driver);
        mailPage = new MainPage(driver);
    }

    @Test
    public void sentFileWithAttach() {
        loginPage.navigateTo("http://gmail.com");
        loginPage.loginAction(USER1_LOGIN, USER1_PASSWORD);
        mailPage.composeMailBtnClick().sendMailToUser(USER2_LOGIN, SUBJECT, CONTENT).attachFile(filePath).clickSendButton();
    }
    @AfterTest
    public void afterTest() {
        mailPage.logoutAction();
    }
}
