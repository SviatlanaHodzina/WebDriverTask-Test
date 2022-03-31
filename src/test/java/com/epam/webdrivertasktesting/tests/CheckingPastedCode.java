package com.epam.webdrivertasktesting.tests;

import com.epam.webdrivertasktesting.model.NewPaste;
import com.epam.webdrivertasktesting.page.NewPasteSavedPage;
import com.epam.webdrivertasktesting.page.PastebinPage;
import com.epam.webdrivertasktesting.service.ComplexPaste;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class CheckingPastedCode extends RequiredConditions {
    @Test(description = "Verifies matching the pasted code" +
                        "to the corresponding code in .properties resource file")
    public void verifyMatchingPastedCode(){
        NewPaste newPaste = ComplexPaste.withCredentialsFromProperty();

        new PastebinPage()
                .openPage()
                .createNewPaste(newPaste);
        String codePasted = new NewPasteSavedPage().getPastedCode();

        assertThat(codePasted, containsString(newPaste.getCode()));
    }
}