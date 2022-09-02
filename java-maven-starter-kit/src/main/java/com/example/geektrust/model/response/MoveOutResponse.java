package com.example.geektrust.model.response;

import com.example.geektrust.model.emums.CommandType;

public class MoveOutResponse implements Response {
    private CommandType commandType;
    private String memberName;

    public MoveOutResponse(CommandType commandType, String memberName) {
        this.commandType = commandType;
        this.memberName = memberName;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
