package com.stephanecharron.command;

import com.stephanecharron.client.ConsoleEntry;
import com.stephanecharron.domain.Game;
import com.stephanecharron.domain.Team;
import com.stephanecharron.error.ErrorConst;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class CommandValidation {

    @Autowired
    private Game game;


    @Before("execution(* com.stephanecharron.command.Command.execute(..)) && args(consoleEntry) && !execution(* com.stephanecharron.command.StartGame.execute(..)) && !execution(* com.stephanecharron.command.Error.execute(..)))")
    public void GameNotStarted(ConsoleEntry consoleEntry) throws RuntimeException {
        if(!game.isStarted()) {
            throw new RuntimeException(ErrorConst.GAME_NOT_STARTED);
        }
    }

    @Before("execution(* com.stephanecharron.command.StartGame.execute(..)) && args(consoleEntry)")
    public void GameStart(ConsoleEntry consoleEntry) throws RuntimeException {
        if(game.isStarted()) {
            throw new RuntimeException(ErrorConst.GAME_IN_PROGRESS);
        }
        if(consoleEntry.getHome().equals(consoleEntry.getVisitor())){
            throw new RuntimeException(ErrorConst.SAME_TEAM_NAME);
        }
    }

    @Before("execution(* com.stephanecharron.command.AddGoal.execute(..)) && args(consoleEntry)")
    public void addGoal (ConsoleEntry consoleEntry) throws RuntimeException {

        Team team =game.getTeamMap().get(consoleEntry.getTeam());
        if(team == null){
            throw new RuntimeException(consoleEntry.getTeam()+ " " + ErrorConst.TEAM_NOT_FOUND);
        }
    }

    @Before("execution(* com.stephanecharron.command.Error.execute(..)) && args(consoleEntry)")
    public void error (ConsoleEntry consoleEntry) throws RuntimeException {
        if(game.isStarted()){
            throw new RuntimeException(ErrorConst.GAME_IN_PROGRESS_BAD_COMMAND);
        }else{
            throw new RuntimeException(ErrorConst.GAME_NOT_STARTED_BAD_COMMAND);
        }

    }

}
