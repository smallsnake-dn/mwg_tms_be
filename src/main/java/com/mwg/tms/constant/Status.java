package com.mwg.tms.constant;

public enum Status {
    CHUA_TAO_YCVC(-1), CHUA_DUYET(0), DUYET(1), HUY(2);
    public int value;
    private Status(int value) {
        this.value = value;
    }
}
