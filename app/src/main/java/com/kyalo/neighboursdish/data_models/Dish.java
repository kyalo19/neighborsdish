package com.kyalo.neighboursdish.data_models;


import java.util.List;

public class Dish {


    private String name;
    private String details;
    private String imageUrl;
    private double price;
    private int pieces;
    private int weight;
    private List<String> mayContain;
    private List<String> category;

    public Dish(){}

    public Dish(String name, String details, String imageUrl, double price, int pieces, int weight, List<String> mayContain, List<String> category) {
        this.name = name;
        this.details = details;
        this.imageUrl = imageUrl;
        this.price = price;
        this.pieces = pieces;
        this.weight = weight;
        this.mayContain = mayContain;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<String> getMayContain() {
        return mayContain;
    }

    public void setMayContain(List<String> mayContain) {
        this.mayContain = mayContain;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}


