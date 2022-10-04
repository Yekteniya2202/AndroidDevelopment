package com.example.lab5;

public class Country {

    private String name = "";
    private int imageId = -1;
    private String territoryDescription = "";

    public Country(String name, int imageId, String territoryDescription) {
        this.name = name;
        this.imageId = imageId;
        this.territoryDescription = territoryDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTerritoryDescription() {
        return territoryDescription;
    }

    public void setTerritoryDescription(String territoryDescription) {
        this.territoryDescription = territoryDescription;
    }
}
