package com.example.onlinecompiler.model;

public class CompilerRequest {

    private String code;
    private String stdIn;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStdIn() {
        return stdIn;
    }

    public void setStdIn(String stdIn) {
        this.stdIn = stdIn;
    }
}
