package com.target.casestudy.domain.request

import javax.validation.Validation

class ProductRequest {
    int id
    String name
    Price current_price

    static class Price {
        BigDecimal value = new BigDecimal(0)
        Currency currency_code
    }

    static enum Currency {
        USD
    }
}
