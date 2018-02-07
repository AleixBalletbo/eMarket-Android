package com.aleixballetbo.interactor;


public interface Interactor<ReturnType> extends Runnable{

    void run();

    <R extends DefaultCallback<ReturnType>> void execute(R defaultCallback);
}
