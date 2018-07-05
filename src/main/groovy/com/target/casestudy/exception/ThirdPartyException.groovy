package com.target.casestudy.exception

import com.target.casestudy.domain.error.ErrorDetails

class ThirdPartyException extends Exception {

    ErrorDetails errorDetails

    ThirdPartyException() {}

    ThirdPartyException(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails
    }


}
