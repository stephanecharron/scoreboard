package com.stephanecharron.client;

import com.stephanecharron.command.ClientCommand;
import org.springframework.stereotype.Component;

/**
 * Created by stephanecharron on 16-08-08.
 */
@Component
public class ConsoleEntry {
    private String home;
    private String visitor;
    private String playerName;
    private int time;
    private String team;
    private ClientCommand command;

    public ConsoleEntry(){}


    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public ClientCommand getCommand() {return command;}

    public void setCommand(ClientCommand command) { this.command = command; }

    @Override
    public String toString() {
        return "ConsoleEntry{" +
                "home='" + home + '\'' +
                ", visitor='" + visitor + '\'' +
                ", playerName='" + playerName + '\'' +
                ", time=" + time +
                ", team='" + team + '\'' +
                ", command=" + command +
                '}';
    }

}
