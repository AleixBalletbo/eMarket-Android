package com.aleixballetbo.interactor;


import com.aleixballetbo.ProductRepository;
import com.aleixballetbo.entities.Product;
import com.aleixballetbo.exception.ErrorBundle;
import com.aleixballetbo.executor.PostExecutionThread;
import com.aleixballetbo.executor.ThreadExecutor;

import javax.inject.Inject;

public class GetProductDetailInteractor extends BaseUseCase<Product> implements Interactor<Product> {

    private GetProductDetailCallback callback;

    public interface GetProductDetailCallback extends DefaultCallback<Product> {}

    ProductRepository.GetProductDetailCallback dataCallback = new ProductRepository.GetProductDetailCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(Product returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };

    private final ProductRepository repository;
    private final ThreadExecutor executor;

    @Inject
    public GetProductDetailInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, ProductRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public void run() {
        repository.getProductDetail(dataCallback);
    }

    @Override
    public <R extends DefaultCallback<Product>> void execute(R defaultCallback) {
        this.callback = ((GetProductDetailCallback) defaultCallback);
        executor.execute(this);
    }
}
