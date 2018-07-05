package com.target.casestudy.exception

import com.target.casestudy.domain.error.ErrorDetails

class UnKnownException extends Exception {
    ErrorDetails errorDetails

    UnKnownException() {}

    UnKnownException(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails
    }
}

