package com.epam.webdrivertasktesting.page;

import com.epam.webdrivertasktesting.driver.WebDriverConnector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    public final int WAIT_TIMEOUT_SECONDS = 50;
    protected WebDriver driver;

    protected AbstractPage() {
        driver = WebDriverConnector.getDriver();
        PageFactory.initElements(driver, this);
    }
    protected abstract AbstractPage openPage();
}