package com.example.john_super;

import java.time.LocalDate;

public class Items {
    /*Item
    Code, Item Name, Item Brand, Price,
    Quantity, Category, Purchased Date and an
    Item Image.*/

    private String code;
    private String itemName;
    private String itemBrand;
    private double price;
    private int quantity;
    private String category;
    private LocalDate purchasedDate;
    private String itemImageAddres;
    private int stockThreshold;

    public Items(String code, String name, String brand, double price, int quantity, String category, LocalDate purchasedDate, String itemImageAddres, int stockThreshold) {
        this.code = code;
        this.itemName = name;
        this.itemBrand = brand;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.purchasedDate = purchasedDate;
        this.itemImageAddres = itemImageAddres;
        this.stockThreshold = stockThreshold;
    }

    public int getStockThreshold() {
        return stockThreshold;
    }

    public void setStockThreshold(int stockThreshold) {
        this.stockThreshold = stockThreshold;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getItemImageAddres() {
        return itemImageAddres;
    }

    public void setItemImageAddres(String itemImageAddres) {
        this.itemImageAddres = itemImageAddres;
    }

    public LocalDate getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(LocalDate purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    void displayItems(){
        System.out.println("code: " + code + "\n" + "name: " + itemName + "\n" + "Brand: " + itemBrand + "\n" + "Price: " + price + "\n" + "Quantity: " + quantity + "\n" + "Category: " + category + "\n" + "Purchased Date: " + purchasedDate + "\n" + "Image Path: " + itemImageAddres + "\n" + "Stock Threshold: " + stockThreshold + "\n");
        System.out.println("____________________________________________________________");

    }
}
