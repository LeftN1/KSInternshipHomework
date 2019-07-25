package com.keepsolid.ksinternshiphomework;

public enum Units{
    KG("kg"),
    GRAMM("g"),
    PCS("pcs"),
    LITER("lt");

    private String value;

    Units(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

}