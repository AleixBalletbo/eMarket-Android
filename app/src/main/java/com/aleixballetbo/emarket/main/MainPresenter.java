package com.aleixballetbo.emarket.main;

import com.aleixballetbo.Model;

import javax.inject.Inject;

public class MainPresenter {
    private final MainView view;
    private final Model model;

    @Inject
    public MainPresenter(MainView view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void onStart () {
        view.changeText(model.getText());
    }
}
