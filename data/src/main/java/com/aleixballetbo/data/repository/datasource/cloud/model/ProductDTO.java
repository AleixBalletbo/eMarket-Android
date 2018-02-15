package com.aleixballetbo.data.repository.datasource.cloud.model;


public class ProductDTO {

    private String _id;
    private String name;
    private int price;
    private String description;
    private String owner;

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }
}
