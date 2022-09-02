package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private String userName;
    private int availableAmount = 0;
    private int spendAmount = 0;
    private ArrayList<Transaction> spend = new ArrayList<>();
    private ArrayList<Dues> dues = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", availableAmount=" + availableAmount +
                ", spendAmount=" + spendAmount +
                ", spend=" + spend +
                ", dues=" + dues +
                '}';
    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<Transaction> getSpend() {
        return spend;
    }

    public void setSpend(Transaction spend) {
        this.spend.add(spend);
    }

    public ArrayList<Dues> getDues() {
        return dues;
    }

    public void setDues(Dues dues) {
        this.dues.add(dues);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public int getSpendAmount() {
        return spendAmount;
    }

    public void setSpendAmount(int spendAmount) {
        this.spendAmount = spendAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getAvailableAmount() == user.getAvailableAmount() && getSpendAmount() == user.getSpendAmount() && getUserName().equals(user.getUserName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getAvailableAmount(), getSpendAmount());
    }

}
