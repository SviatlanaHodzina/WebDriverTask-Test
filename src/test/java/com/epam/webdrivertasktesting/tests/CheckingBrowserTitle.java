package com.epam.webdrivertasktesting.tests;

import com.epam.webdrivertasktesting.model.NewPaste;
import com.epam.webdrivertasktesting.page.NewPasteSavedPage;
import com.epam.webdrivertasktesting.page.PastebinPage;
import com.epam.webdrivertasktesting.service.ComplexPaste;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class CheckingBrowserTitle extends RequiredConditions {
    @Test(description = "Verifies matching browser page's Name/ Title " +
                        "to the corresponding value in .properties resource file")
    public void verifyMatchingOfBrowserPageTitleToPastedTitle(){
        NewPaste newPaste = ComplexPaste.withCredentialsFromProperty();

        new PastebinPage()
                .openPage()
                .createNewPaste(newPaste);
        String browserPageTitle = new NewPasteSavedPage().getBrowserPageTitle();

        assertThat(browserPageTitle, containsString(newPaste.getNameTitle()));
    }
}