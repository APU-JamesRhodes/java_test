package com.example.geektrust.model;

public class Dues {
    private String lentBy;
    private int amount = 0;

    public Dues() {
    }

    public Dues(String lentBy, int amount) {
        this.amount = amount;
        this.lentBy = lentBy;
    }

    public String getLentBy() {
        return lentBy;
    }

    public void setLentBy(String lentBy) {
        this.lentBy = lentBy;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
