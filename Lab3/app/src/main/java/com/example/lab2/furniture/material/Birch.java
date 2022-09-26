package com.example.lab2.furniture.material;

public class Birch implements FurnitureMaterial{
    private final double cost = 20L;

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String toString(){
        return "Берёза";
    }
}
