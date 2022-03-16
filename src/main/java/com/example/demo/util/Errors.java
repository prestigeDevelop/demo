package com.example.demo.util;

public enum Errors {
    SYSTEM_ERROR(5000),
    EMAIL_EXIST_ERROR(501),
    EMAIL_EMPTY(502),
    PASSWORD_EMPTY(503);

    int error;
    Errors(int error){
    this.error=error;
    }

    public int getError() {
        return error;
    }
}
