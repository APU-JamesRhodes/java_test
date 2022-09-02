package com.example.geektrust.utils;

import com.example.geektrust.model.emums.CommandType;
import com.example.geektrust.model.response.*;

import java.util.ArrayList;

public class CommandExtractor {
    private CommandExtractor(){

    }
    public static CommandExtractor build(){
        return new CommandExtractor();
    }
    public Response response(String command) {
        String[] splitResponse = command.split(" ");
        if (splitResponse.length == 0) {
            return new ErrorResponse("FAILURE");
        }
        CommandType commandType = getType(splitResponse[0]);

        if (commandType == CommandType.MOVE_IN) {
            if (splitResponse.length > 2) {
                return new ErrorResponse("FAILURE");
            }
            MoveInResponse build = new MoveInResponse(CommandType.MOVE_IN,
                    splitResponse[1]);
            return build;
        } else if (commandType == CommandType.SPEND) {
            if (splitResponse.length < 4) {
                return new ErrorResponse("FAILURE");
            }
            ArrayList<String> name = new ArrayList<>();
            for (int i = 3; i < splitResponse.length; i++) {
                name.add(splitResponse[i]);
            }
            SpendResponse build = new SpendResponse(
                    commandType,
                    Integer.valueOf(splitResponse[1]),
                    name,
                    splitResponse[2]);
            return build;
        } else if (commandType == CommandType.DUES) {
            if (splitResponse.length > 2) {
                return new ErrorResponse("FAILURE");
            }
            DuesResponse build = new DuesResponse(commandType, splitResponse[1]);
            return build;
        } else if (commandType == CommandType.CLEAR_DUE) {
            if (splitResponse.length > 4) {
                return new ErrorResponse("FAILURE");
            }
            ClearDueResponse build = new ClearDueResponse(
                    commandType,
                    splitResponse[1],
                    splitResponse[2],
                    Integer.valueOf(splitResponse[3]));
            return build;
        } else if (commandType == CommandType.MOVE_OUT) {
            if (splitResponse.length > 2) {
                return new ErrorResponse("FAILURE");
            }
            MoveOutResponse build = new MoveOutResponse(commandType, splitResponse[1]);
            return build;
        }
        return new ErrorResponse("FAILURE");
    }

    private CommandType getType(String command) {
        if (command.equals("MOVE_IN")) {
            return CommandType.MOVE_IN;
        } else if (command.equals("SPEND")) {
            return CommandType.SPEND;
        } else if (command.equals("DUES")) {
            return CommandType.DUES;
        } else if (command.equals("CLEAR_DUE")) {
            return CommandType.CLEAR_DUE;
        } else {
            return CommandType.MOVE_OUT;
        }
    }
}
