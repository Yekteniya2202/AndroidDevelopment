package com.example.lab2.furniture.material;

public class Linden implements FurnitureMaterial{
    private final double cost = 35L;

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String toString(){
        return "Липа";
    }
}
