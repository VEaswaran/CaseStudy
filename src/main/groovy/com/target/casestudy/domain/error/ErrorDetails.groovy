package com.target.casestudy.domain.error

class ErrorDetails implements Serializable {

    public static final GENERIC_USER_MESSAGE = "Please try we had an issue, but it should be fixed now"
    String userMessage
    String debugMessage
    String errorCode

    ErrorDetails() {}

    ErrorDetails(String userMessage, String debugMessage, String errorCode) {
        this.userMessage = userMessage
        this.debugMessage = debugMessage
        this.errorCode = errorCode
    }
}
