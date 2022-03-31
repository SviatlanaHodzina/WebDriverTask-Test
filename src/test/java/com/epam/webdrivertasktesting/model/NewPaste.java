package com.epam.webdrivertasktesting.model;

import java.util.Objects;

public class NewPaste {
    private String code;
    private String syntaxHighLighting;
    private String pasteExpiration;
    private String nameTitle;

    public NewPaste(String code, String syntaxHighlighting,
                    String pasteExpiration,
                    String nameTitle) {
        this.code = code;
        this.syntaxHighLighting = syntaxHighlighting;
        this.pasteExpiration = pasteExpiration;
        this.nameTitle = nameTitle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSyntaxHighLighting() {
        return syntaxHighLighting;
    }

    public void setSyntaxHighLighting(String syntaxHighLighting) {
        this.syntaxHighLighting = syntaxHighLighting;
    }

    public String getPasteExpiration() {
        return pasteExpiration;
    }

    public void setPasteExpiration(String pasteExpiration) {
        this.pasteExpiration = pasteExpiration;
    }

    public String getNameTitle() {
        return nameTitle;
    }

    public void setNameTitle(String nameTitle) {
        this.nameTitle = nameTitle;
    }

    @Override
    public String toString() {
        return "NewPaste{" +
               "code='" + code + '\'' +
               ", syntaxHighLighting='" + syntaxHighLighting + '\'' +
               ", pasteExpiration='" + pasteExpiration + '\'' +
               ", nameTitle='" + nameTitle + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewPaste)) return false;
        NewPaste newPaste = (NewPaste) o;
        return Objects.equals(getCode(), newPaste.getCode())
               && Objects.equals(getSyntaxHighLighting(), newPaste.getSyntaxHighLighting())
               && Objects.equals(getPasteExpiration(), newPaste.getPasteExpiration())
               && Objects.equals(getNameTitle(), newPaste.getNameTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getSyntaxHighLighting(), getPasteExpiration(), getNameTitle());
    }
}