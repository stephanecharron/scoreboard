package com.stephanecharron.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stephanecharron on 16-08-08.
 */
public class Team {
    private String name;
    private List<Goal> goals;

    public Team (String name){
        this.name = name;
        this.goals = new ArrayList<>();
    }

    public void addGaol (Goal goal){
        goals.add(goal);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    private void setGoals(List<Goal> goals) {
        this.goals = goals;
    }public void end() {
    }

    @Override
    public String toString() {
        String ret = "";
        ret += getName();
        ret += " ";
        ret += getGoals().size();
        ret += ' ';
        if(getGoals().size() > 0){
            Goal last = getGoals().get(getGoals().size()-1);

            ret += '(';

            for (Goal goal : goals) {
                ret += goal.toString();
                if(goal != last){
                    ret += ", ";
                }
            }
            ret += ')';
        }

        return ret;
    }

}
