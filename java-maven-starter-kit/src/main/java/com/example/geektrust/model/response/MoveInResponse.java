package com.example.geektrust.model.response;

import com.example.geektrust.model.emums.CommandType;

public class MoveInResponse implements Response{
    private CommandType commandType;
    private String nameOfTheMember;

    public MoveInResponse(CommandType commandType, String nameOfTheMember) {
        this.commandType = commandType;
        this.nameOfTheMember = nameOfTheMember;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public String getNameOfTheMember() {
        return nameOfTheMember;
    }

    public void setNameOfTheMember(String nameOfTheMember) {
        this.nameOfTheMember = nameOfTheMember;
    }
}
