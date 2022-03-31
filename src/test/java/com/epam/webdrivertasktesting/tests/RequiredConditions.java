package com.epam.webdrivertasktesting.tests;

import com.epam.webdrivertasktesting.driver.WebDriverConnector;
import com.epam.webdrivertasktesting.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class RequiredConditions {
    public static WebDriver driver;

    @BeforeMethod()
        public void setUp() {
        driver = WebDriverConnector.getDriver();
    }

//    @AfterMethod(alwaysRun = true)
//    public void stopBrowser() {
//        WebDriverConnector.closeDriver();
//    }
}