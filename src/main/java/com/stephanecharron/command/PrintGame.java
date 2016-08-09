package com.stephanecharron.command;

import com.stephanecharron.client.ConsoleEntry;
import com.stephanecharron.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrintGame implements Command {
    @Autowired
    private Game game;

    @Override
    public void execute(ConsoleEntry consoleEntry) {
        System.out.println(game.toString());
    }
}
