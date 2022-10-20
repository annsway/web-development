package com.itheima.domain;

public class Product {
    private String id;
    private String name;
    private Integer pnum;
    private Double price;
    private String category;

    public Product(String id, String name, Integer pnum, Double price, String category) {
        this.id = id;
        this.name = name;
        this.pnum = pnum;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", pnum=" + pnum +
                ", category='" + category + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



}
