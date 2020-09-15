package com.ishika.onlineshopping.Model;

public class Register {
    private String message_success,message_error,message;

    public Register(String message_success, String message_error, String message) {
        this.message_success = message_success;
        this.message_error = message_error;
        this.message = message;
    }

    public String getMessage_success() {
        return message_success;
    }

    public void setMessage_success(String message_success) {
        this.message_success = message_success;
    }

    public String getMessage_error() {
        return message_error;
    }

    public void setMessage_error(String message_error) {
        this.message_error = message_error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
