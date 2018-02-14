package com.aleixballetbo.interactor;


import com.aleixballetbo.ProductRepository;
import com.aleixballetbo.entities.Product;
import com.aleixballetbo.exception.ErrorBundle;
import com.aleixballetbo.executor.PostExecutionThread;
import com.aleixballetbo.executor.ThreadExecutor;

import java.util.List;

import javax.inject.Inject;

public class GetProductsInteractor extends BaseUseCase<List<Product>> implements Interactor<Void, List<Product>> {

    private GetProductsCallback callback;

    public interface GetProductsCallback extends DefaultCallback<List<Product>> {}

    ProductRepository.GetProductsCallback dataCallback = new ProductRepository.GetProductsCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(List<Product> returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };

    private final ProductRepository repository;
    private final ThreadExecutor executor;

    @Inject
    public GetProductsInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, ProductRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public void run() {
        repository.getProducts(dataCallback);
    }

    @Override
    public <R extends DefaultCallback<List<Product>>> void execute(Void input, R defaultCallback) {
        this.callback = ((GetProductsCallback) defaultCallback);
        executor.execute(this);
    }
}
