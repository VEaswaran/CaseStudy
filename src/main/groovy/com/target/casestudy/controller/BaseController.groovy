package com.target.casestudy.controller

import com.target.casestudy.domain.common.Constant
import com.target.casestudy.exception.NumberNotFoundException
import com.target.casestudy.representation.ProductRepresentation
import groovy.util.logging.Slf4j
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException

@Slf4j
class BaseController {

    def handleException(Exception exception, ProductRepresentation productRepresentation) {
        if (exception instanceof ConnectException || exception instanceof HttpServerErrorException || exception instanceof HttpClientErrorException) {
            productRepresentation.errorCode = Constant.INTERNAL_SERVER_ERROR
            productRepresentation.userMessage = Constant.USER_MESSAGE_ISE
            log.error(Constant.DEBUG_MESSAGE_ISE)
            log.error(Constant.INTERNAL_SERVER_ERROR, exception.cause)
        } else if (exception instanceof NumberNotFoundException) {
            productRepresentation.errorCode = Constant.NUMBER_NOT_FOUND
            productRepresentation.userMessage = Constant.NUMBER_NOT_FOUND_UMSG
        } else {
            productRepresentation.errorCode = Constant.UNKNOWN_EXCEPTION
            productRepresentation.userMessage = Constant.USE_MESSAGE_UE
            log.error(Constant.DEBUG_MESSAGE_UE)
            log.error(Constant.INTERNAL_SERVER_ERROR, exception.cause)
        }

    }

}
