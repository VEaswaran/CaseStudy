package com.target.casestudy;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.target.casestudy.domain.product.Product;
import com.target.casestudy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 */
@Order(value=2)
@Component
@Profile("development")
public class InitialDataSetUp implements CommandLineRunner {

    private static final String TABLE_NAME = "product";

    @Autowired
    ProductRepository productRepository;

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keySpace;

    @Value("${spring.data.cassandra.contact-points}")
    private String points;

    private String deleteTableQuery = "DROP TABLE IF EXISTS product_schema." + TABLE_NAME;
    private String createTableQuery = "CREATE TABLE product(id int PRIMARY KEY, name varchar," +
            " price double, currency varchar);";

    @Override
    public void run(String... args) {
        Cluster cluster = Cluster.builder().addContactPoint(points).build();
        Session session = cluster.connect(keySpace);
        session.execute(deleteTableQuery);
        session.execute(createTableQuery);
        insertData();
    }

    private void insertData() {
        Product product = new Product(13860428, "The Big Lebowski (Blu-ray)", 89.56, "USD");
        productRepository.save(product);
    }
}
