package com.aleixballetbo;


import com.aleixballetbo.entities.Product;
import com.aleixballetbo.interactor.DefaultCallback;
import com.aleixballetbo.interactor.GetProductsInteractor;

import java.util.List;

public interface ProductRepository {

    interface GetProductsCallback extends DefaultCallback<List<Product>> {}

    void getProducts(GetProductsCallback callback);
}
