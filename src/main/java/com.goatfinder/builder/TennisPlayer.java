package com.goatfinder.builder;

public class TennisPlayer extends SportsGoat {


    TennisPlayer(String name, int age, Stats sNBA){
        this.age = age;
        this.name = name;
        this.playerStats = sNBA;
    }


    public void goatCase(){
        String proof = "My name is " + this.name + " and I play a game suited for gentlemen.\n" +
                "I get to travel the world and dismantle opponents with with hand-eye coordination.\n" +
                "They say I am the 'GOAT' and I wouldn't dare disagree. \n";
        System.out.println(proof + "\n\n" + "Goat Report");
    }






}
