package com.stephanecharron.command;

import com.stephanecharron.client.ConsoleEntry;
import com.stephanecharron.domain.Game;
import com.stephanecharron.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartGame implements Command {
    @Autowired
    public Game game;

    @Override
    public void execute(ConsoleEntry consoleEntry) {
        game.start(new Team(consoleEntry.getHome()), new Team(consoleEntry.getVisitor()));
    }
}
