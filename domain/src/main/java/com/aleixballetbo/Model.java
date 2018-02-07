package com.aleixballetbo;

import javax.inject.Inject;

public class Model {

    @Inject
    public Model () {

    }

    public String getText() {
        return "Hello world!";
    }
}
