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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
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