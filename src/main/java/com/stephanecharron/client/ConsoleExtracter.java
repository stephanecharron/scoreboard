package com.stephanecharron.client;

import com.stephanecharron.ScoreboardBaseListener;
import com.stephanecharron.ScoreboardLexer;
import com.stephanecharron.ScoreboardParser;
import com.stephanecharron.command.ClientCommand;
import org.antlr.v4.runtime.*;
import org.springframework.stereotype.Component;

/**
 * Created by stephanecharron on 16-08-08.
 */

@Component
public class ConsoleExtracter {
    public ConsoleExtracter() {
    }

    public ConsoleEntry parse(String input) throws Exception {
        ScoreboardLexer scoreboardLexer = new ScoreboardLexer(new ANTLRInputStream(input));
        ScoreboardParser scoreboardParser = new ScoreboardParser(new CommonTokenStream(scoreboardLexer));
        ConsoleEntry consoleEntry = new ConsoleEntry();

        scoreboardLexer.removeErrorListeners();
        scoreboardParser.removeErrorListeners();

        scoreboardParser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                consoleEntry.setCommand(ClientCommand.ERROR);
            }
        });

        scoreboardParser.addParseListener(new ScoreboardBaseListener() {
            @Override
            public void exitStart(ScoreboardParser.StartContext ctx) {
                consoleEntry.setCommand(ClientCommand.START);
                consoleEntry.setHome(ctx.home.getText());
                consoleEntry.setVisitor(ctx.visitor.getText());
            }

            @Override
            public void exitEnd(ScoreboardParser.EndContext ctx) {
                consoleEntry.setCommand(ClientCommand.END);
            }

            @Override
            public void exitPrint(ScoreboardParser.PrintContext ctx) {
                consoleEntry.setCommand(ClientCommand.PRINT);
            }

            @Override
            public void exitAddGoal(ScoreboardParser.AddGoalContext ctx) {
                consoleEntry.setCommand(ClientCommand.ADD_GOAL);
                consoleEntry.setPlayerName(ctx.playerName.getText().trim());
                consoleEntry.setTime(Integer.parseInt(ctx.time.getText()));
                consoleEntry.setTeam(ctx.team.getText());
            }

        });

        scoreboardParser.field();

        return consoleEntry;

    }


}
