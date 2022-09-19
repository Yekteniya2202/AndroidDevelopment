package com.example.lab2.furniture.type;

public class Chair implements FurnitureType{
    private final double cost = 70L;

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String toString(){
        return "Стул";
    }
}
