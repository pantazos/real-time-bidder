package com.projectagora.realtimebidder.domain;

/**
 * Enumeration - Operation Systems (OS)
 */
public enum OS {
    ANDROID("Android"),
    IOS("iOS");

    private final String abbreviation;

    OS(final String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
