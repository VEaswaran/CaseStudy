package com.target.casestudy.service

import com.target.casestudy.client.RestClient
import com.target.casestudy.domain.common.Constant
import com.target.casestudy.domain.product.Product
import com.target.casestudy.repository.ProductRepository
import com.target.casestudy.representation.ProductRepresentation
import org.springframework.web.client.HttpServerErrorException
import spock.lang.Specification

class ProductServiceSpecification extends Specification {
    ProductService productService
    ProductRepository productRepository
    RestClient restClient

    def setup() {
        productRepository = Mock(ProductRepository)
        restClient = Mock(RestClient)
        productService = new ProductService(restClient: restClient, productRepository: productRepository)
    }

    def "[TestCase] Method to test Product Service class"() {
        given:
        Product product = new Product(id: 1, name: "BluRay", price: 11.23, currency: "USD")
        Map response = ["product": ["item": ["product_description": ["title": "BluRay"]]]]
        when:
        ProductRepresentation productRepresentation = productService.getProductDetails(1)

        then:
        productRepresentation.name == "BluRay"
        productRepresentation.current_price.currency_code as String == "USD"
        restClient.get(_ as URI) >> response
        productRepository.findById(1) >> product
    }


    def "[TestCase] Method to test Product Service class with exception"() {
        given:
        Product product = new Product(id: 1, name: "BluRay", price: 11.23, currency: "USD")
        Map response = ["product": ["item": ["product_description": ["title": "BluRay"]]]]
        Exception exception
        when:
        try {
            ProductRepresentation productRepresentation = productService.getProductDetails(1)
        } catch (Exception ex) {
            exception = ex
        }
        then:
        exception.errorDetails.errorCode == Constant.UNKNOWN_EXCEPTION
        restClient.get(_ as URI) >> { throw new HttpServerErrorException() }
    }
}
