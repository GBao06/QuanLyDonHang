package com.noithat.model;

import java.util.ArrayList;
import java.util.List;

public class Combo implements OrderComponent {
    private String name;
    private List<OrderComponent> components = new ArrayList<>();

    public Combo(String name) {
        this.name = name;
    }

    public void add(OrderComponent component) {
        components.add(component);
    }

    public void remove(OrderComponent component) {
        components.remove(component);
    }

    @Override
    public double getTotalPrice() {
        double total = 0;
        for (OrderComponent c : components) {
            total += c.getTotalPrice();
        }
        return total;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "+ " + name + " : " + getTotalPrice());
        for (OrderComponent c : components) {
            c.print(indent + "   ");
        }
    }
}