package com.stephanecharron.command;

import com.stephanecharron.client.ConsoleEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CommandHandler {

    @Autowired
    public EndGame endGame;

    @Autowired
    public StartGame startGame;

    @Autowired
    public PrintGame printGame;

    @Autowired
    public AddGoal addGoal;

    @Autowired
    public Error errorConst;

    private  HashMap<ClientCommand,Command> commandMap;

    private void init(){
        commandMap = new HashMap<>();
        commandMap.put(ClientCommand.END, endGame);
        commandMap.put(ClientCommand.START, startGame);
        commandMap.put(ClientCommand.PRINT, printGame);
        commandMap.put(ClientCommand.ADD_GOAL, addGoal);
        commandMap.put(ClientCommand.ERROR, errorConst);
    }

    private Command getCommand (ClientCommand command){

        return commandMap.get(command);
    }

    public void handle (ConsoleEntry consoleEntry){
        if(commandMap == null){
            init();
        }

        Command command = getCommand(consoleEntry.getCommand());
        try {
            command.execute(consoleEntry);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}
