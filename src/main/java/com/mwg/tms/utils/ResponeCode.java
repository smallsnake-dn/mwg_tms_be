package com.mwg.tms.utils;

public enum ResponeCode {
    SUCCESS(1),
    ERROR(0);
    public final int code;
    private ResponeCode(int code) {
        this.code = code;
    }
}
