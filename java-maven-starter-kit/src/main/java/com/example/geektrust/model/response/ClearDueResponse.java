package com.example.geektrust.model.response;

import com.example.geektrust.model.emums.CommandType;

public class ClearDueResponse implements Response {
    private CommandType commandType;
    private String whoOwes;
    private String whoLent;
    private int amount;

    public ClearDueResponse(CommandType commandType, String whoOwes, String whoLent, int amount) {
        this.commandType = commandType;
        this.whoOwes = whoOwes;
        this.whoLent = whoLent;
        this.amount = amount;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public String getWhoOwes() {
        return whoOwes;
    }

    public void setWhoOwes(String whoOwes) {
        this.whoOwes = whoOwes;
    }

    public String getWhoLent() {
        return whoLent;
    }

    public void setWhoLent(String whoLent) {
        this.whoLent = whoLent;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
