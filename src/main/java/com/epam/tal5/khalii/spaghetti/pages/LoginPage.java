package com.epam.tal5.khalii.spaghetti.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.NoSuchElementException;

public class LoginPage extends BasePage {
    public static final String EMAIL_FIELD_ID = "Email";
    public static final String BUTTON_NEXT_ID = "next";
    public static final String PASSWORD_FIELD_ID = "Passwd";
    public static final String SIGN_IN_ID = "signIn";
    private static final String COMPOSE_BTN_XPATH = "//div[@role='button' and @gh and contains(@class, 'T')]";

    @FindBy(id = EMAIL_FIELD_ID)
    private WebElement emailField;

    @FindBy(id = BUTTON_NEXT_ID)
    private WebElement nextButton;

    @FindBy(id = PASSWORD_FIELD_ID)
    private WebElement passwordField;

    @FindBy(id = SIGN_IN_ID)
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage fillLogin(String login){
        emailField.sendKeys(login);
        return this;
    }

    public LoginPage clickNext(){
        nextButton.click();
        return this;
    }

    public LoginPage fillPassword(String password){
        passwordField.sendKeys(password);
        return this;
    }

    public MainPage signIn(){
        signInButton.click();
        return new MainPage(driver);
    }

    public MainPage loginAction(String login, String password) {
        fillLogin(login);
        try {
            clickNext();
        } catch (NoSuchElementException e) {

        } finally {
            fillPassword(password).signIn();
        }
        PageHelper.waitForElementPresent(getDriver(), COMPOSE_BTN_XPATH);
        Assert.assertTrue(getDriver().getTitle().contains(login));
        return new MainPage(driver);
    }


}
