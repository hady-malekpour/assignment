package com.edia.exception;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.util.Assert;

/**
 * Created by hadi on 7/25/2016.
 */
public class ErrorResponse {
    String message;

    public ErrorResponse(Throwable ex) {
        Assert.notNull(ex);
        message = ExceptionUtils.getRootCauseMessage(ex);
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

