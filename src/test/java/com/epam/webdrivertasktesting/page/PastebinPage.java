package com.epam.webdrivertasktesting.page;

import com.epam.webdrivertasktesting.model.NewPaste;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.xpath;

public class PastebinPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();

    public final String PASTEBIN_URL = "https://pastebin.com";
    public static final String INPUT_NAME_TITLE_XPATH = "//input[@id='postform-name']";

    @FindBy(xpath = "html/body")
    public static WebElement pastebinPageContent;

    @FindBy(xpath = "//vli[@id='hideSlideBanner']")
    public static WebElement hideBannerElement;

    @FindBy(xpath = "//div[@class='popup-box -cookies']/span")
    public static WebElement okUseCookiesElement;

    @FindBy(xpath = "//div[@title='Close Me']")
    public static WebElement closePopUpSignUpElement;

    @FindBy(xpath = "//label[@for='paste-raw-on']")
    public static WebElement toggleControl;

    @FindBy(xpath = "html/body//textarea[@id='postform-text']")
    public static WebElement inputCode;

    @FindBy(xpath = "//button[@class='btn -big']")
    public static WebElement createNewPasteButton;

    @FindBy(xpath = "//span[contains(text(),'%s')]")
    public static WebElement expiration;

    public final String BUTTON_SYNTAX_HIGHLIGHTING_XPATH = "//span[@id='select2-postform-format-container']";
//    This WebElement is locateed in the #shadow-root(user-agent) and is out of touch
//    public final String SYNTAX_HIGHLIGHTING_XPATH = "//span[contains(text(),'%s')]";
    public final String SYNTAX_HIGHLIGHTING_XPATH = "//input[@role='searchbox']";
    public final String BUTTON_PASTE_EXPIRATION_XPATH = "//span[@id='select2-postform-expiration-container']";
    public final String PASTE_EXPIRATION_XPATH = "//span[contains(text(),'%s')]";

    @Override
    public PastebinPage openPage() {
        driver.navigate().to(PASTEBIN_URL);
        new WebDriverWait(driver, ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(pastebinPageContent));
        logger.info("Pastebin page is loaded");
        return this;
    }

    public PastebinPage hideBanner() {
        new WebDriverWait(driver, ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(hideBannerElement));
        hideBannerElement.click();
        logger.info("The popup banner is hided");
        return this;
    }

    public PastebinPage okUseCookies() {
        new WebDriverWait(driver, ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(okUseCookiesElement));
        okUseCookiesElement.click();
        logger.info("Agreement to use of cookies: \"Ok, I Understand\" is clicked");
        return this;
    }

    public PastebinPage closePopUpSignUp() {
        new WebDriverWait(driver, ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(closePopUpSignUpElement));
        closePopUpSignUpElement.click();
        logger.info("The PopUp\"Sign Up\" box is closed");
        return this;
    }

    public PastebinPage toggleControl() {
        new WebDriverWait(driver, ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(toggleControl));
        toggleControl.click();
        logger.info("Toggle control for Syntax Highlighting is turned on");
        return this;
    }

    public PastebinPage inputNewPasteCode(String code) {
        new WebDriverWait(driver, ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(inputCode));
        inputCode.click();
        inputCode.sendKeys(code);
        logger.info("New Paste Code is: " + inputCode.getAttribute("value"));
        return this;
    }

    public PastebinPage inputSyntaxHighlighting(String syntaxHighLighting) {
        new WebDriverWait(driver, ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(xpath(BUTTON_SYNTAX_HIGHLIGHTING_XPATH))).click();
        WebElement syntaxHighLIGHTING = driver.findElement(xpath(SYNTAX_HIGHLIGHTING_XPATH));
        syntaxHighLIGHTING.click();
        syntaxHighLIGHTING.sendKeys(syntaxHighLighting);
        syntaxHighLIGHTING.sendKeys(Keys.ENTER);
        logger.info("Paste Highlighting: " + syntaxHighLighting);
        return this;
    }

    //    This method isn't usable as it doesn't work for the reason below:
//        The WebElement with xpath SYNTAX_HIGHLIGHTING_XPATH is locateed
//        in the #shadow-root(user-agent) and is out of touch
    public PastebinPage inputPasteExpiration(String pasteExpiration) {
        new WebDriverWait(driver, ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(xpath(BUTTON_PASTE_EXPIRATION_XPATH))).click();
        JavascriptExecutor je = (JavascriptExecutor) driver;
        if (driver.findElement((xpath(String.format(PASTE_EXPIRATION_XPATH, pasteExpiration)))).isEnabled()) {
            je.executeScript("arguments[0].scrollIntoView(true);", expiration);
            new WebDriverWait(driver, ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                    .until(ExpectedConditions.visibilityOf(expiration)).click();
            logger.info("Paste Expiration: " + pasteExpiration);
        } else driver.findElement((xpath(BUTTON_PASTE_EXPIRATION_XPATH))).sendKeys(Keys.ESCAPE);
        logger.info("Selection of the Expiration is disable");
        return this;
    }

    public PastebinPage pasteNameTitle(String nameTitle) {
        WebElement nameTitleElement = driver.findElement(xpath(INPUT_NAME_TITLE_XPATH));
        nameTitleElement.click();
        nameTitleElement.sendKeys(nameTitle);
        logger.info("Paste Name/ Title: " + nameTitleElement.getAttribute("value"));
        return this;
    }

    public NewPasteSavedPage createNewPaste(NewPaste newPaste) {
        hideBanner()
                .okUseCookies()
                .closePopUpSignUp();
        inputNewPasteCode(newPaste.getCode());

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)", "");

        inputSyntaxHighlighting(newPaste.getSyntaxHighLighting());
//        inputPasteExpiration(newPaste.getPasteExpiration());
        pasteNameTitle(newPaste.getNameTitle());
        createNewPasteButton.click();
        logger.info("New Paste is created: ");
        return new NewPasteSavedPage();
    }
}