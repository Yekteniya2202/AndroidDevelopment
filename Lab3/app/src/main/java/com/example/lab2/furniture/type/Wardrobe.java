package com.example.lab2.furniture.type;

public class Wardrobe implements FurnitureType{
    private final double cost = 500L;

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String toString(){
        return "Шкаф";
    }
}
