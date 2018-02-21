package com.aleixballetbo.data.repository.datasource;


import com.aleixballetbo.entities.Product;

import java.io.IOException;
import java.util.List;

public interface DataSource {

    List<Product> getProducts () throws IOException;

    Product getProductDetail (String productId) throws IOException;

    void addProduct (Product product) throws IOException;
}
