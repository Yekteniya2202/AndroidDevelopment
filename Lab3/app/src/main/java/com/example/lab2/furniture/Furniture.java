package com.example.lab2.furniture;

import com.example.lab2.furniture.color.FurnitureColor;
import com.example.lab2.furniture.material.FurnitureMaterial;
import com.example.lab2.furniture.type.FurnitureType;

import java.io.Serializable;

public class Furniture implements Serializable {

    private final double costPerWarrantyYear = 5L;
    private FurnitureType furnitureType;
    private FurnitureColor furnitureColor;
    private FurnitureMaterial furnitureMaterial;
    private boolean coveredWithLacquer = false;
    private int warranty = 3;

    public FurnitureType getFurnitureType() {
        return furnitureType;
    }

    public void setFurnitureType(FurnitureType furnitureType) {
        this.furnitureType = furnitureType;
    }

    public FurnitureColor getFurnitureColor() {
        return furnitureColor;
    }

    public void setFurnitureColor(FurnitureColor furnitureColor) {
        this.furnitureColor = furnitureColor;
    }

    public FurnitureMaterial getFurnitureMaterial() {
        return furnitureMaterial;
    }

    public void setFurnitureMaterial(FurnitureMaterial furnitureMaterial) {
        this.furnitureMaterial = furnitureMaterial;
    }

    public void setCoveredWithLacquer(boolean coveredWithLacquer) {
        this.coveredWithLacquer = coveredWithLacquer;
    }

    public boolean isCoveredWithLacquer() {
        return coveredWithLacquer;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) throws Exception {
        if (warranty > 10) throw new Exception("Мы не предоставляем гарантию больше 10 лет");
        this.warranty = warranty;
    }

    public void throwIfComponentsNull() throws Exception {
        if (furnitureType == null || furnitureColor == null || furnitureMaterial == null) throw new Exception("Furniture order is not completed");
    }

    public double getTotalCost() throws Exception {
        double lacquerCost = coveredWithLacquer ? 10L : 0L;
        return furnitureType.getCost() + furnitureColor.getCost() + furnitureMaterial.getCost() + warranty * costPerWarrantyYear + lacquerCost;
    }

}
