package com.aleixballetbo.emarket.AddProduct;


import com.aleixballetbo.interactor.AddProductInteractor;

public class AddProductPresenter {

    private final AddProductView view;
    private final AddProductInteractor addProductInteractor;

    public AddProductPresenter(AddProductView view, AddProductInteractor addProductInteractor) {
        this.view = view;
        this.addProductInteractor = addProductInteractor;
    }

    public void addProduct (String name, int price, String description, String owner) {

    }
}
