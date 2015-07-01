package com.epam.tal5.khalii.spaghetti.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MessagePage extends BasePage{
    public static final String FORM_TO_XPATH = "//textarea[@name='to']";
    public static final String FORM_SUBJ_XPATH = "//input[@name='subjectbox']";
    public static final String FORM_TEXT_XPATH = "//div[@role='textbox']";
    public static final String FORM_SENDBTN_XPATH = "//tbody//div[count(div) = 2]/div[@role='button' and @data-tooltip]";
    public static final String FORM_SAVE_AND_QUITBTN_XPATH = "//img[3]";
    public final static String ATTACH_FILES_BTN = "//div[@command = 'Files']/div/div/div";
    public static final String PROGRESS_BAR_LINK = "//div[@role='progressbar']";

    @FindBy(xpath = ATTACH_FILES_BTN)
    private WebElement attachFileButton;

    @FindBy(xpath = FORM_TO_XPATH)
    private WebElement toField;

    @FindBy(xpath = FORM_SUBJ_XPATH)
    private WebElement subjectField;

    @FindBy(xpath = FORM_TEXT_XPATH)
    private WebElement textField;

    @FindBy(xpath = FORM_SENDBTN_XPATH)
    private WebElement sendButton;

    @FindBy(xpath = FORM_SAVE_AND_QUITBTN_XPATH)
    private WebElement saveAndQuitButton;

    public MessagePage inputSenderReceiver(String email) {
        toField.sendKeys(email);
        return this;
    }

    public MessagePage inputSubject(String subject) {
        subjectField.sendKeys(subject);
        return this;
    }

    public MessagePage inputContent(String text) {
        textField.sendKeys(text);
        return this;
    }

    public MainPage clickSendButton() {
        sendButton.click();
        return new MainPage(driver);
    }

    public MessagePage attachFileButtonClick() {
        attachFileButton.click();
        return this;
    }

    public MessagePage(WebDriver driver ) {
        super(driver);
    }

    public MessagePage sendMailToUser(String userName, String messageSubject, String messageContent) {
        PageHelper.waitForElementPresent(getDriver(), FORM_TO_XPATH);
        inputSenderReceiver(userName).inputSubject(messageSubject)
                .inputContent(messageContent);
        return this;
    }

    public MessagePage attachFile(String filePath) {
        attachFileButtonClick();
        PageHelper.delay(PageHelper.MIDDLE_DELAY);
        PageHelper.uploadFile(filePath);
        PageHelper.waitForElementPresent(getDriver(), PROGRESS_BAR_LINK);
        return this;
    }
}
