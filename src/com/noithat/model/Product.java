package com.noithat.model;

public class Product implements OrderComponent {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public double getTotalPrice() {
        return price * quantity;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "- " + name + " (x" + quantity + ") : " + getTotalPrice());
    }
}