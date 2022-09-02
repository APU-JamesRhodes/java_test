package com.example.geektrust.model;

import java.util.ArrayList;

public class Transaction {
    private int amount = 0;
    private String spendBy;
    private ArrayList<String> spendFor = new ArrayList<>();

    public Transaction() {
    }

    public Transaction(int amount, String spendBy, ArrayList<String> spendFor) {
        this.amount = amount;
        this.spendBy = spendBy;
        this.spendFor = spendFor;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSpendBy() {
        return spendBy;
    }

    public void setSpendBy(String spendBy) {
        this.spendBy = spendBy;
    }

    public ArrayList<String> getSpendFor() {
        return spendFor;
    }

    public void setSpendFor(ArrayList<String> spendFor) {
        this.spendFor = spendFor;
    }


}
