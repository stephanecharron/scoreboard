package com.stephanecharron.domain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by stephanecharron on 16-08-08.
 */
@Component
public class Game {
    private Team home;
    private Team visitor;
    private boolean started;
    private Map<String, Team> teamMap;

    public Game(){
        teamMap =  new HashMap<>();
        setStarted(false);
    }

    public void start(Team home, Team visitor){
        setStarted(true);
        setHome(home);
        setVisitor(visitor);
        teamMap.put(home.getName(),home);
        teamMap.put(visitor.getName(),visitor);
    }
    public void end() {
        setHome(null);
        setVisitor(null);
        setStarted(false);
    }

    public Team getHome() {
        return home;
    }

    private void setHome(Team home) {
        this.home = home;
    }

    public Team getVisitor() {
        return visitor;
    }

    private void setVisitor(Team visitor) {
        this.visitor = visitor;
    }

    public boolean isStarted() {
        return started;
    }

    private void setStarted(boolean started) {
        this.started = started;
    }

    public Map<String, Team> getTeamMap() {
        return teamMap;
    }

    public void setTeamMap(Map<String, Team> teamMap) {
        this.teamMap = teamMap;
    }

    @Override
    public String toString() {
        return visitor.toString() + " vs. " + home.toString();
    }

}
