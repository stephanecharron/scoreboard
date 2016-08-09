package com.stephanecharron;

import com.stephanecharron.client.ConsoleEntry;
import com.stephanecharron.client.ConsoleExtracter;
import com.stephanecharron.command.ClientCommand;
import com.stephanecharron.command.CommandHandler;
import com.stephanecharron.command.Error;
import com.stephanecharron.config.TestConfig;
import com.stephanecharron.domain.Game;
import com.stephanecharron.domain.Goal;
import com.stephanecharron.error.ErrorConst;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringApplicationConfiguration(classes = TestConfig.class)
public class ScoreboardApplicationTests {

    @Autowired
    ConsoleExtracter consoleExtracter;

    @Autowired
    CommandHandler commandHandler;

    @Autowired
    Game game;

    @Autowired
    Error error;


    @Test
    public void CommandExtractingForStartGame() throws Exception {
        ConsoleEntry consoleEntry = consoleExtracter.parse("Start: 'a' vs. 'b'");

        assertEquals("Visitor team name is a", consoleEntry.getVisitor(), "b");
        assertEquals("Home team name is b", consoleEntry.getHome(), "a");
        assertEquals("Command is START", consoleEntry.getCommand(), ClientCommand.START);
    }

    @Test
    public void CommandExtractingForPrint() throws Exception {
        ConsoleEntry consoleEntry = consoleExtracter.parse("print");

        assertEquals("Command is Print", consoleEntry.getCommand(), ClientCommand.PRINT);
    }

    @Test
    public void CommandExtractingForEnd() throws Exception {
        ConsoleEntry consoleEntry = consoleExtracter.parse("End");

        assertEquals("Command is End", consoleEntry.getCommand(), ClientCommand.END);
    }

    @Test
    public void CommandExtractingForAddGoal() throws Exception {
        ConsoleEntry consoleEntry = consoleExtracter.parse("12 'team name' playerName");

        assertEquals("Command is ADD GOAL", consoleEntry.getCommand(), ClientCommand.ADD_GOAL);
        assertEquals("time is 12", consoleEntry.getTime(), 12);
        assertEquals("team is team name", consoleEntry.getTeam(), "team name");
        assertEquals("player is player name", consoleEntry.getPlayerName(), "playerName");
    }

    @Test
    public void StartGame() throws Exception {
        commandHandler.handle(consoleExtracter.parse("Start: 'a' vs. 'b'"));
        assertEquals(game.getVisitor().getName(), "b");
        assertEquals(game.getHome().getName(), "a");
        assertEquals(game.isStarted(),true);
    }

    @Test
    public void AddGoal() throws Exception {
        commandHandler.handle(consoleExtracter.parse("Start: 'a' vs. 'b'"));
        commandHandler.handle(consoleExtracter.parse("12 'a'  qwqw"));
        assertEquals("one goal is added for home team",game.getHome().getGoals().size() , 1);
        Goal goal = game.getHome().getGoals().get(0);
        assertEquals(goal.getPlayerNane(),"qwqw");
        assertEquals(goal.getTime(),12);
    }

    @Test
    public void EndGame() throws Exception {
        commandHandler.handle(consoleExtracter.parse("Start: 'a' vs. 'b'"));
        commandHandler.handle(consoleExtracter.parse("End"));
        assertEquals(game.isStarted(),false);
    }

    @Test(expected = RuntimeException.class)
    public void commandErrorThrowsExecption() throws RuntimeException  {
        error.execute(new ConsoleEntry());
    }

    @Test
    public void errorSameNameTeam()  {
        try {
            commandHandler.handle(consoleExtracter.parse("Start: 'a' vs. 'a'"));
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorConst.SAME_TEAM_NAME);
        }
    }

    @Test
    public void gameNotStated()  {
        try {
            commandHandler.handle(consoleExtracter.parse("print"));
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorConst.GAME_NOT_STARTED);
        }
        try {
            commandHandler.handle(consoleExtracter.parse("End"));
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorConst.GAME_NOT_STARTED);
        }

        try {
            commandHandler.handle(consoleExtracter.parse("12 'dsd' sdsd"));
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorConst.GAME_NOT_STARTED);
        }
    }

    @Test
    public void teamNotFoundForGoalWithTeamNameNotExist()   {
        try {
            commandHandler.handle(consoleExtracter.parse("Start: 'a' vs. 'b'"));
        } catch (Exception e) {
        }

        try {
            commandHandler.handle(consoleExtracter.parse("12 'aa' sdsd"));
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorConst.TEAM_NOT_FOUND);
        }
    }

    @Test
    public void gameAlreadyStated()  {
        try {
            commandHandler.handle(consoleExtracter.parse("Start: 'a' vs. 'b'"));
        } catch (Exception e) {
        }

        try {
            commandHandler.handle(consoleExtracter.parse("Start: 'a' vs. 'b'"));
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorConst.GAME_IN_PROGRESS);
        }
    }

    @Test
    public void badCommandGameNotStarted()  {
        try {
            commandHandler.handle(consoleExtracter.parse("asdasdad"));
        } catch (Exception e) {
            assertEquals(e.getMessage(),ErrorConst.GAME_NOT_STARTED_BAD_COMMAND);
        }
    }

    @Test
    public void badCommandGameStarted()  {
        try {
            commandHandler.handle(consoleExtracter.parse("Start: 'a' vs. 'b'"));
            commandHandler.handle(consoleExtracter.parse("asdasdad"));
        } catch (Exception e) {
            assertEquals(e.getMessage(),ErrorConst.GAME_IN_PROGRESS_BAD_COMMAND);
        }
    }

}
