package com.example.geektrust.model.response;

import com.example.geektrust.model.emums.CommandType;

public class DuesResponse implements Response {
    private CommandType commandType;
    private String owesBy;
    private int amount = 0;

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public String getOwesBy() {
        return owesBy;
    }

    public void setOwesBy(String owesBy) {
        this.owesBy = owesBy;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public DuesResponse(CommandType commandType, String owesBy) {
        this.commandType = commandType;
        this.owesBy = owesBy;
    }
}
