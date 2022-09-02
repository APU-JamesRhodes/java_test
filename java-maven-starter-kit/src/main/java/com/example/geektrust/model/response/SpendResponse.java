package com.example.geektrust.model.response;

import com.example.geektrust.model.emums.CommandType;

import java.util.ArrayList;

public class SpendResponse implements Response {
    private CommandType commandType;
    private int amount = 0;
    private ArrayList<String> spendFor = new ArrayList<>();
    private String spendBy;

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public SpendResponse(CommandType commandType,int amount, ArrayList<String> spendFor, String spendBy) {
        this.commandType=commandType;
        this.amount = amount;
        this.spendFor = spendFor;
        this.spendBy = spendBy;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ArrayList<String> getSpendFor() {
        return spendFor;
    }

    public void setSpendFor(ArrayList<String > spendFor) {
        this.spendFor = spendFor;
    }

    public String getSpendBy() {
        return spendBy;
    }

    public void setSpendBy(String spendBy) {
        this.spendBy = spendBy;
    }
}
