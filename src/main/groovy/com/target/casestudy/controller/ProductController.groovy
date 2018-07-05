package com.target.casestudy.controller

import com.target.casestudy.builder.ProductBuilder
import com.target.casestudy.builder.ProductRepresentationBuilder
import com.target.casestudy.domain.common.Constant
import com.target.casestudy.domain.product.Product
import com.target.casestudy.domain.request.ProductRequest
import com.target.casestudy.representation.ProductRepresentation
import com.target.casestudy.service.ProductService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid


/**
 * Controller to handle get and put method  for  a product which read from external system and store in DB
 */
@RestController
@RequestMapping
@Slf4j
class ProductController extends BaseController {


    @Autowired
    ProductService productService

    @GetMapping("/products/{id}")
    ResponseEntity getProductData(@PathVariable("id") int id) {
        log.info("Request Id:{}", id)
        ProductRepresentation productRepresentation = new ProductRepresentation()
        HttpStatus status = HttpStatus.OK
        try {
            productRepresentation = productService.getProductDetails(id)
            log.info("Successfully processed of request:{}", productRepresentation?.id)
        } catch (Exception ex) {
            handleException(ex, productRepresentation)
            status = HttpStatus.INTERNAL_SERVER_ERROR
        }
        return new ResponseEntity<>(productRepresentation, status)
    }

    @PutMapping("/products/{id}")
    ResponseEntity updateProductData(@Valid @RequestBody ProductRequest productRequest, @PathVariable("id") int id) {
        ProductRepresentation productRepresentation = new ProductRepresentation()
        HttpStatus status = HttpStatus.OK
        if (id == productRequest?.id) {
            log.info("Update request for Id:{}", productRequest?.id)
            try {
                Product product = ProductBuilder.builder().withProductMap(productRequest).build()
                productRepresentation = productService.updateProductDetails(product)
                log.info("Successfully processed")
            } catch (Exception ex) {
                handleException(ex, productRepresentation)
                status = productRepresentation.errorCode.equalsIgnoreCase(Constant.NUMBER_NOT_FOUND) ?
                        HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR
            }
        } else {
            productRepresentation.errorCode == Constant.NUMBER_NOT_MATCH
            productRepresentation.userMessage = Constant.NUMBER_NOT_MATCH_UMSG
        }
        return new ResponseEntity<>(productRepresentation, status)
    }


}
