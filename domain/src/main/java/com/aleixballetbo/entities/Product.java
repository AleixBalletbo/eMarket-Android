package com.aleixballetbo.entities;


public class Product {

    private String id;
    private String name;
    private float price;
    private String description;
    private String owner;


    public Product(String id, String name, float price, String description, String owner) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }
}
