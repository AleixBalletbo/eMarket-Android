package com.aleixballetbo.interactor;


public interface Interactor<InputType, ReturnType> extends Runnable{

    void run();

    <R extends DefaultCallback<ReturnType>> void execute(InputType inputType, R defaultCallback);
}
