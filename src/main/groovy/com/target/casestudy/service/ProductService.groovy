package com.target.casestudy.service


import com.target.casestudy.builder.ProductRepresentationBuilder
import com.target.casestudy.client.RestClient
import com.target.casestudy.domain.product.Product
import com.target.casestudy.exception.NumberNotFoundException
import com.target.casestudy.repository.ProductRepository
import com.target.casestudy.representation.ProductRepresentation
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

/**
 *
 * ProductService is a Service class to handle business cases
 *
 */
@Service
@Slf4j
class ProductService {

    @Autowired
    ProductRepository productRepository

    @Autowired
    RestClient restClient

    @Cacheable(value = "product", unless = "#result==null") // to store the values in ehcache
    ProductRepresentation getProductDetails(int productId) {
        try {
            URI uri = new URI("https://redsky.target.com/v2/pdp/tcin/${productId}?excludes=taxonomyy,price," +
                    "promotion,bulk_ship,rating_and_reviews,rating_and_review_statistics,question_answer_statistics")
            Map response = restClient.get(uri)
            Product product = productRepository.findById(productId)
            if (product == null){throw new NumberNotFoundException() }
            ProductRepresentationBuilder.builder().withProduct(product, response).build()
        } catch (Exception ex) {
            throw ex
        }
    }


    ProductRepresentation updateProductDetails(Product product) {
        ProductRepresentation productRepresentation = null
        try {
            productRepresentation = new ProductRepresentation()
            Product productVerification = productRepository.findById(product?.id)
            if (productVerification == null) { throw new NumberNotFoundException() }
            productRepository.saveById(new BigDecimal(product.price), product.id)
            productRepresentation.status = ProductRepresentation.ServiceStatus.UPDATED_SUCCESSFULLY
        } catch (Exception ex) {
            throw ex
        }
        productRepresentation
    }
}
