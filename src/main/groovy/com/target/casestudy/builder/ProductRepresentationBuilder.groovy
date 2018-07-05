package com.target.casestudy.builder

import com.target.casestudy.domain.product.Product
import com.target.casestudy.representation.ProductRepresentation

/**
 * ProductRepresentationBuilder
 */
class ProductRepresentationBuilder {

    ProductRepresentation productRepresentation

    private ProductRepresentationBuilder() {
        productRepresentation = new ProductRepresentation()
        productRepresentation.current_price = new ProductRepresentation.Price()
        productRepresentation
    }

    static ProductRepresentationBuilder builder() {
        return new ProductRepresentationBuilder()
    }

    ProductRepresentation build() {
        return productRepresentation
    }

    ProductRepresentationBuilder withProduct(Product product, Map prdResponse) {
        productRepresentation.id = prdResponse?.product?.item?.tcin ? Integer.valueOf(prdResponse?.product?.item?.tcin) : product.id
        productRepresentation.name =prdResponse?.product?.item?.product_description?.title ? prdResponse?.product?.item?.product_description?.title : null
        productRepresentation.current_price.value = product?.price
        productRepresentation.current_price.currency_code = ProductRepresentation.Currency.valueOf(product?.currency)
        return this
    }

}
