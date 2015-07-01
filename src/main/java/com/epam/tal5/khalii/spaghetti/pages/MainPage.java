package com.epam.tal5.khalii.spaghetti.pages;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MainPage extends BasePage  {

    private static final String COMPOSE_BTN_XPATH = "(//div[@id and @class]/div/div[@role='button' and @gh])[2]";
    private static final String SENT_MAIL_TAB_XPATH = ".//a[contains(@href,'#sent')]";
    private static final String SPAM_TAB_XPATH = "//a[contains(@href, 'spam')]";
    private static final String TO_SPAM_BTN_XPATH = "//div[@act and @role='button'][2]/div/div";
    private static final String LOGOUT_BTN_XPATH = "//a[contains(@href, 'logout')]";
    private static final String USER_LOGO_XPATH = "//a[contains(@href, 'SignOutOptions')]";
    private static final String CONFIRMATION_XPATH = "//div[@aria-live='assertive' and @role='alert' and @aria-atomic='true']/div/div[2]";
    private static final String SETTINGS = "//div[contains(@role,'button') and (@title='Настройки')]";
    private static final String SETTINGS_THEME = "//div[@id and @role='menu']//div[9]/div ";
    private static final String SECOND_USER = "//div[2]/div[2]/a[1]/img";
    private static final String ADD_NEW_USER = "//a[contains(@href,'https://accounts.google.com/AddSession')]";
    private static final String NEW_LETTER_XPATH = " //div[@role='tabpanel']//table//tbody/tr[1]/td[2]/div/div";
    private static final String MORE_TABS_BUTTON_XPATH = "//span[@id and @class and @role='button']/span/div";
    private static final String ASSERT_LETTER_SUBJECT = "//table[@role='presentation']//h2[@tabindex='-1']";
    private static final String STARRED_TAB_XPATH = "//a[contains(@href, 'starred')]";
    private static final String INBOX_TAB_XPATH = "//a[contains(@href, 'inbox') and @aria-label]";
    private static final String NEW_LETTER_SUBJECT = "//table//tbody//tr[1]/td[6]//span[1]";
    private static final String NEW_LETTER_CONTENT = "//table//tbody//tr[1]/td[6]//span[2]";


    @FindBy(xpath = INBOX_TAB_XPATH)
    private WebElement inboxBtn;


    @FindBy(xpath = ASSERT_LETTER_SUBJECT)
    private WebElement letterSubject;

    @FindBy(xpath = NEW_LETTER_CONTENT)
    private WebElement letterContent;

    @FindBy(xpath = STARRED_TAB_XPATH)
    private WebElement starredTab;

    @FindBy(xpath = MORE_TABS_BUTTON_XPATH)
    private WebElement moreTabsBtn;

    @FindBy(xpath = SPAM_TAB_XPATH)
    private WebElement spamTab;

    @FindBy(xpath = CONFIRMATION_XPATH)
    private WebElement confirmationAlert;

    @FindBy(xpath = TO_SPAM_BTN_XPATH)
    private WebElement toSpamBtn;

    @FindBy(xpath = NEW_LETTER_XPATH)
    private WebElement newLetter;

    @FindBy(xpath = COMPOSE_BTN_XPATH)
    private WebElement composeBTN;

    @FindBy(xpath = SENT_MAIL_TAB_XPATH)
    private WebElement sentMenuItem;

    @FindBy(xpath = USER_LOGO_XPATH)
    private WebElement userLogo;

    @FindBy(xpath = SECOND_USER)
    private WebElement secondUserLogo;

    @FindBy(xpath = LOGOUT_BTN_XPATH)
    private WebElement logOutBTN;

    @FindBy(xpath = ADD_NEW_USER)
    private WebElement addUserBTN;

    @FindBy(xpath = SETTINGS)
    private WebElement settingBTN;

    @FindBy(xpath = SETTINGS_THEME)
    private WebElement settingThemeBTN;

    private WebElement getLetterSubject() {
        try {
            return letterSubject;
        } catch (ElementNotVisibleException | ElementNotFoundException e) {
            return getDriver().findElement(By.xpath(NEW_LETTER_SUBJECT + "/b"));
        }
    }

    private WebElement getLetterContent() {

        return letterContent;
    }

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private MainPage spamTabClick() {
        PageHelper.waitForElementPresent(getDriver(), SPAM_TAB_XPATH);
        spamTab.click();
        return this;
    }

    public MainPage goToStarredClick() {
        starredTab.click();
        return this;
    }

    private MainPage moreTabsBtnClick() {
        moreTabsBtn.click();
        return this;
    }

    private MainPage toSpamBtnClick() {
        toSpamBtn.click();
        return this;
    }

    private MainPage newLetterClick() {
        newLetter.click();
        return this;
    }

    public MessagePage composeMailBtnClick() {
        composeBTN.click();
        return new MessagePage(driver);
    }

    private LoginPage logOutBtnClick() {
        logOutBTN.click();
        return new LoginPage(driver);
    }

    private MainPage userLogoClick() {
        userLogo.click();
        return this;
    }

    private LoginPage addUserBTNClick() {
        PageHelper.waitForElementPresent(getDriver(), ADD_NEW_USER);
        addUserBTN.click();
        return new LoginPage(driver);
    }

    private MainPage secondUserLogoClick() {
        secondUserLogo.click();
        return this;
    }

    private MainPage settingBTNClick() {
        settingBTN.click();
        return this;
    }

    private MainPage settingThemeBTNClick() {
        PageHelper.waitForElementPresent(getDriver(), SETTINGS_THEME);
        settingThemeBTN.click();
        return this;
    }

    public ThemesPage openThemeSettings() {
        settingBTNClick();
        settingThemeBTNClick();
        return new ThemesPage(driver);
    }

    public MainPage logoutAction() {

        userLogoClick();
        PageHelper.waitForElementPresent(driver, LOGOUT_BTN_XPATH);
        try {
            logOutBtnClick();
        } catch (UnhandledAlertException e) {
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            } catch (AWTException e1) {
                e1.printStackTrace();
            }
        }
        return this;
    }

    public MainPage addOrSwithUser(String login, String password) {
        PageHelper.waitForElementPresent(getDriver(), USER_LOGO_XPATH);

        userLogoClick();
        try {
            PageHelper.waitForElementPresent(getDriver(), SECOND_USER);
            secondUserLogoClick();
            ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(newTab.get(2));
        } catch (NoSuchElementException | ElementNotVisibleException e) {
            addUser(login, password);
        }
        return this;
    }

    private MainPage addUser(String login, String password) {
        String old = driver.getWindowHandle();
        addUserBTNClick();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
        new LoginPage(driver).loginAction(login, password);
        return this;
    }

    public MainPage moveToSpam() {
        newLetterClick();
        toSpamBtnClick();
        Assert.assertNotNull(confirmationAlert);
        return this;
    }

    public MainPage goToSpamFolder() {
        moreTabsBtnClick();
        PageHelper.delay(PageHelper.SEC_DELAY);
        spamTabClick();
        PageHelper.delay(PageHelper.SEC_DELAY);
        return this;
    }

    public MainPage dragAndDropMessage() {

        PageHelper.waitForElementPresent(getDriver(), NEW_LETTER_XPATH);
        Actions actions = new Actions(driver);

        actions.dragAndDrop(newLetter, starredTab).release().perform();
        PageHelper.delay(6000L);
        Assert.assertNotNull(confirmationAlert);
        return this;
    }

    public MainPage assertThatLetterMoved(String subject, String content) {
        PageHelper.waitForElementPresent(getDriver(), NEW_LETTER_SUBJECT);
        letterSubject.click();
        PageHelper.waitForElementPresent(getDriver(), ASSERT_LETTER_SUBJECT);
        Assert.assertTrue(letterSubject.getText().contains(subject));
        return this;
    }

}

