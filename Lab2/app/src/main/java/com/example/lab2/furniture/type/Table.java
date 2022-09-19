package com.example.lab2.furniture.type;

public class Table implements FurnitureType {

    private final double cost = 100L;

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String toString(){
        return "Стол";
    }
}
