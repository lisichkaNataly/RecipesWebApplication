package me.izm.model;

public class Ingredient {

    private final String id;
    private final String name;
    private int count;
    private String measurement;

    public Ingredient(String id, String name, int count, String measurement) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.measurement = measurement;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return count;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setQuantity(int quantity) {
        this.count = quantity;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getId() {
        return id;
    }
}
