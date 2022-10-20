package com.example.hw03;

public class Product {
    private String PID;
    private String prodName;
    private String prodDesc;
    private double price;
    private int qty;

    public Product(String PID, String prodName, String prodDesc, double price, int qty) {
        this.PID = PID;
        this.prodName = prodName;
        this.prodDesc = prodDesc;
        this.price = price;
        this.qty = qty;
    }


    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) { // this is the qty in the shopping cart
        this.qty = qty;
    }
}
