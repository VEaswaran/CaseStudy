package com.target.casestudy.builder

import com.target.casestudy.domain.product.Product
import com.target.casestudy.domain.request.ProductRequest
import spock.lang.Specification

class ProductBuilderSpecification extends Specification {


    def "[TestCase] Method to test ProductBuilder"() {
        given:
        ProductRequest.Price price = new ProductRequest.Price()
        price.currency_code = ProductRequest.Currency.USD
        price.value = value
        ProductRequest productRequest = new ProductRequest(id: id, name: "BlueRay", current_price: price)

        when:
        Product product = ProductBuilder.builder().withProductMap(productRequest).build()

        then:
        assert product.id == id
        assert product.price == (value ? value : 0)

        where:
        scenario              | id | value
        "Happy path scenario" | 1  | 85.79
        "Happy path scenario" | 2  | null


    }

}
