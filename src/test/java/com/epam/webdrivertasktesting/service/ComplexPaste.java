package com.epam.webdrivertasktesting.service;

import com.epam.webdrivertasktesting.model.NewPaste;

public class ComplexPaste {
    public static final String TESTDATA_CODE = "testdata.newPaste.code";
    public static final String TESTDATA_SYNTAX_HIGHLIGHTING = "testdata.newPaste.syntaxHighLighting";
    public static final String TESTDATA_PASTE_EXPIRATION = "testdata.newPaste.pasteExpiration";
    public static final String TESTDATA_PASTE_NAME_TITLE = "testdata.newPaste.nameTitle";

    @org.jetbrains.annotations.NotNull
    @org.jetbrains.annotations.Contract(" -> new")
    public static NewPaste withCredentialsFromProperty() {
        return new NewPaste(TestDataReader.getTestData(TESTDATA_CODE),
                TestDataReader.getTestData(TESTDATA_SYNTAX_HIGHLIGHTING),
                TestDataReader.getTestData(TESTDATA_PASTE_EXPIRATION),
                TestDataReader.getTestData(TESTDATA_PASTE_NAME_TITLE));
    }
}