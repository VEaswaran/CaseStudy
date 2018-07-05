package com.target.casestudy.repository

import com.target.casestudy.domain.product.Product
import org.springframework.context.annotation.Bean
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.data.cassandra.repository.Query
import org.springframework.data.repository.CrudRepository

public interface ProductRepository extends CassandraRepository<Product> {


    @Query(value = "SELECT * FROM product WHERE id=?0")
    public Product findById(int id)


    @Query(value =  "UPDATE product SET price=?0 WHERE id=?1")
    public Product saveById(BigDecimal price, int id)


}