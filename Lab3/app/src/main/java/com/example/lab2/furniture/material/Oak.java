package com.example.lab2.furniture.material;

public class Oak implements FurnitureMaterial{
    private final double cost = 50L;

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String toString(){
        return "Дуб";
    }
}
