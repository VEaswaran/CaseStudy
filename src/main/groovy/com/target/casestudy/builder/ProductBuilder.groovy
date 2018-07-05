package com.target.casestudy.builder

import com.target.casestudy.domain.product.Product
import com.target.casestudy.domain.request.ProductRequest

class ProductBuilder {

    Product product

    private ProductBuilder() {
        product = new Product()
    }

    static ProductBuilder builder() {
        return new ProductBuilder()
    }

    Product build() {
        return product
    }

    ProductBuilder withProductMap(ProductRequest request) {
        product.id = request?.id
        product.name = request?.name
        product.price = request?.current_price?.value ? request?.current_price?.value : 0
        product.currency = request?.current_price?.currency_code
        this

    }


}
