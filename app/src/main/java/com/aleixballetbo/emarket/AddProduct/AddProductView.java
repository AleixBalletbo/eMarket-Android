package com.aleixballetbo.emarket.AddProduct;

public interface AddProductView {

    void onSuccess();

    void showError(String error);

    void showErrorProductName (String error);

    void showErrorProductPrice (String error);

    void showErrorProductOwner (String error);

    void showErrorProductDescription (String error);
}
