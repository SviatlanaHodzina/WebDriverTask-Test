package com.epam.webdrivertasktesting.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.xpath;

public class NewPasteSavedPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();

    public static final String SYNTAX_HIGHLIGHTED_XPATH = "//a[contains(text(),'%s')]";

    @FindBy(xpath = "//ol")
    private WebElement codePasted;

    @FindBy(xpath = "//h1")
    private WebElement browserPageTitle;

    @Override
    public NewPasteSavedPage openPage() {
        new WebDriverWait(driver, Duration.ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(browserPageTitle));
        logger.info("New Paste is created");
        return this;
    }

    public String getBrowserPageTitle() {
        new WebDriverWait(driver, ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(browserPageTitle));
        String titleOfTheNewPastePage = browserPageTitle.getAttribute("innerText");
        logger.info("Browser Page Title is " + titleOfTheNewPastePage);
        return titleOfTheNewPastePage;
    }

    public String getSyntaxHighlighted(String syntaxHighLighting) {
        WebElement syntaxHighLighted = driver.findElement
                (((xpath(String.format(SYNTAX_HIGHLIGHTED_XPATH, syntaxHighLighting)))));
        String syntaxHighLightedAttribute = syntaxHighLighted.getAttribute("innerText");
        logger.info("Syntax Highlighted for: " + syntaxHighLightedAttribute);
        return syntaxHighLightedAttribute;
    }
    public String getPastedCode() {
        new WebDriverWait(driver, ofSeconds(this.WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(codePasted));
        String codeAttribute = codePasted.getAttribute("textContent");
        logger.info("Syntax Highlighted for: " + codeAttribute);
        return codeAttribute;
    }
}