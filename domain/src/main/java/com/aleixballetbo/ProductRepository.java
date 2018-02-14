package com.aleixballetbo;


import com.aleixballetbo.entities.Product;
import com.aleixballetbo.interactor.DefaultCallback;

import java.util.List;

public interface ProductRepository {

    interface GetProductsCallback extends DefaultCallback<List<Product>> {}

    interface GetProductDetailCallback extends DefaultCallback<Product> {}

    void getProducts(GetProductsCallback callback);

    void getProductDetail(GetProductDetailCallback callback);
}
