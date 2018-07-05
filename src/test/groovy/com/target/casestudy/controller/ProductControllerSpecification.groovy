package com.target.casestudy.controller

import com.target.casestudy.exception.ThirdPartyException
import com.target.casestudy.exception.UnKnownException
import com.target.casestudy.representation.ProductRepresentation
import com.target.casestudy.service.ProductService
import spock.lang.Specification

class ProductControllerSpecification extends Specification {

    ProductController productController
    ProductService productService

    def setup() {
        productService = Mock(ProductService)
        productController = new ProductController(productService: productService)
    }


    def "[TestCase] Method to test Get call"() {
        ProductRepresentation productRepresentation = new ProductRepresentation()
        ProductRepresentation response
        when:
        try {
            response = productController.getProductData(1)
        } catch (Exception ex) {

            if (input == 2) {
                assert ex instanceof ThirdPartyException
            }
            if (input == 3) {
                assert ex instanceof UnKnownException
            }
        }

        then:
        if (input == 1) {
            productService.getProductDetails(1) >> productRepresentation
            response != null
        } else if (input == 2) {
            productService.getProductDetails(1) >> { throw new ThirdPartyException() }
        } else {
            productService.getProductDetails(1) >> { throw new UnKnownException() }
        }

        where:
        scenario                                        | input
        "success Scenario"                              | 1
        "failure Scenario with third party exception"   | 2
        "failure Scenario with unknown party exception" | 3

    }
}
