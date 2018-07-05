package com.target.casestudy.representation

import com.fasterxml.jackson.annotation.JsonInclude
import com.target.casestudy.domain.error.ErrorDetails
import jnr.ffi.annotations.In

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProductRepresentation extends ErrorDetails implements Serializable {
    Integer id
    String name
    Price current_price
    ServiceStatus status

    static class Price {
        BigDecimal value = new BigDecimal(0)
        Currency currency_code
    }

    static enum Currency {
        USD
    }

    static enum ServiceStatus {
        UPDATED_SUCCESSFULLY
    }
}