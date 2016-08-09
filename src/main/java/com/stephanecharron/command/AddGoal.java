package com.stephanecharron.command;

import com.stephanecharron.client.ConsoleEntry;
import com.stephanecharron.domain.Game;
import com.stephanecharron.domain.Goal;
import com.stephanecharron.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AddGoal implements Command {
    @Autowired
    private Game game;

    @Override
    public void execute(ConsoleEntry consoleEntry) {
        Team team =game.getTeamMap().get(consoleEntry.getTeam());
        if(team != null){
            team.addGaol(new Goal(consoleEntry.getTime(),consoleEntry.getPlayerName()));
        }
    }
}
