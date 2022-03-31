package com.epam.webdrivertasktesting.tests;

import com.epam.webdrivertasktesting.model.NewPaste;
import com.epam.webdrivertasktesting.page.NewPasteSavedPage;
import com.epam.webdrivertasktesting.page.PastebinPage;
import com.epam.webdrivertasktesting.service.ComplexPaste;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class CheckingSyntaxHighlighted extends RequiredConditions {
    @Test(description = "Verifies matching of Syntax Highlighted " +
            "to the corresponding value in .properties resource file")
    public void verifySyntaxHighlighted() {
        NewPaste newPaste = ComplexPaste.withCredentialsFromProperty();

        new PastebinPage()
                .openPage()
                .createNewPaste(newPaste);
        new NewPasteSavedPage();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)", "");
        String syntaxHighlighted = new NewPasteSavedPage().getSyntaxHighlighted(newPaste.getSyntaxHighLighting());

        assertThat(syntaxHighlighted, containsString(newPaste.getSyntaxHighLighting()));
    }
}