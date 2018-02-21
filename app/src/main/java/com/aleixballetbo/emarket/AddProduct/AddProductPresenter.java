package com.aleixballetbo.emarket.AddProduct;


import com.aleixballetbo.entities.Product;
import com.aleixballetbo.exception.ErrorBundle;
import com.aleixballetbo.interactor.AddProductInteractor;

public class AddProductPresenter {

    private final AddProductView view;
    private final AddProductInteractor addProductInteractor;

    public AddProductPresenter(AddProductView view, AddProductInteractor addProductInteractor) {
        this.view = view;
        this.addProductInteractor = addProductInteractor;
    }

    public void addProduct (String name, String price, String description, String owner) {
        boolean correct = true;
        if (name.isEmpty()) {
            view.showErrorProductName("Cal introduir un nom pel producte");
            correct = false;
        }
        if (price.isEmpty()) {
            view.showErrorProductPrice("Cal introduir un preu pel producte");
            correct = false;
        }
        if (description.isEmpty()) {
            view.showErrorProductDescription("Cal introduir una descripció pel producte");
            correct = false;
        }
        if (owner.isEmpty()) {
            view.showErrorProductOwner("Cal introduir un correu de contacte");
            correct = false;
        }
        if (correct) {
            Product product = new Product(name, Integer.parseInt(price), description, owner);
            addProductInteractor.execute(product, new AddProductInteractor.AddProductCallback() {
                @Override
                public void onError(ErrorBundle errorBundle) {
                    view.showError(errorBundle.getErrorMessage());
                }

                @Override
                public void onSuccess(Void returnParam) {
                    view.onSuccess();
                }
            });
        }
    }
}
