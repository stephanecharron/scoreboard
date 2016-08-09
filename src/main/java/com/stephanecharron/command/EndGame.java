package com.stephanecharron.command;

import com.stephanecharron.client.ConsoleEntry;
import com.stephanecharron.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EndGame implements Command {
    @Autowired
    private Game game;

    @Override
    public void execute(ConsoleEntry consoleEntry) {
        game.end();
    }
}
