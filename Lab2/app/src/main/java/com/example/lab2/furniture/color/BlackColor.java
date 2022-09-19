package com.example.lab2.furniture.color;

public class BlackColor implements FurnitureColor{
    private final double cost = 5L;

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String toString(){
        return "Черный";
    }
}
