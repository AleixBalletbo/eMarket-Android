package com.aleixballetbo.interactor;


import com.aleixballetbo.ProductRepository;
import com.aleixballetbo.entities.Product;
import com.aleixballetbo.exception.ErrorBundle;
import com.aleixballetbo.executor.PostExecutionThread;
import com.aleixballetbo.executor.ThreadExecutor;

import javax.inject.Inject;

public class AddProductInteractor extends BaseUseCase<Void> implements Interactor<Product, Void> {

    private AddProductCallback callback;

    public interface AddProductCallback extends DefaultCallback<Void> {}

    ProductRepository.AddProductCallback dataCallback = new ProductRepository.AddProductCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(Void returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };

    private final ProductRepository repository;
    private final ThreadExecutor executor;
    private Product product;

    @Inject
    public AddProductInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, ProductRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public void run() {
        repository.addProduct(product, dataCallback);
    }

    @Override
    public <R extends DefaultCallback<Void>> void execute(Product input, R defaultCallback) {
        this.product = input;
        this.callback = ((AddProductCallback) defaultCallback);
        executor.execute(this);
    }
}
