package com.dal.languagetranslation;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Language {

    private String code;

    public Language(String inputCode) {
        this.code = inputCode;
    }

    public String getDisplayName() {
        return new Locale(code).getDisplayName();
    }

    public String getCode() {
        return this.code;
    }

    @NonNull
    public String toString() {
        return code + " - " + getDisplayName();
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
