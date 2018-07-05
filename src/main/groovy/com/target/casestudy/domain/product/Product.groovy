package com.target.casestudy.domain.product

import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

@Table
class Product {

    @PrimaryKey
    int id
    String name
    Double price
    String currency

    public Product() {
    }

    public Product(int id, String name, Double price, String currency) {
        this.id = id
        this.name = name
        this.price = price
        this.currency = currency
    }


}
