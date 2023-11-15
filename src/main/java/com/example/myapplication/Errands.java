package com.example.myapplication;


public class Errands {

    public static final Errands[] tasks = {
            new Errand("Morning Run", "Due", 1),
            new Errand("Django Database Modify", "Due", 2),
            new Errand("Drive to Deir Ghassaneh", "Due", 3),
            new Errand("Water the Trees", "Due", 4),
            new Errand("Finish Anagram Scramble", "Due", 5),
            new Errand("Fix in-game Time", "Due", 6)
    };

    @Override
    public String toString(){return "";}
}