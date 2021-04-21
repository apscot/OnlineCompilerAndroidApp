package com.example.onlinecompiler.model;

import java.io.Serializable;

public class CompilerResponse implements Serializable {

    private String stdOut;
    private String stdErr;

    public String getStdOut() {
        return stdOut;
    }

    public void setStdOut(String stdOut) {
        this.stdOut = stdOut;
    }

    public String getStdErr() {
        return stdErr;
    }

    public void setStdErr(String stdErr) {
        this.stdErr = stdErr;
    }
}
