package com.goatfinder.builder;

public class GoatFactory {

    public static IGoat createGoat(String name, String position, int age, Stats stats){
        return new BasketBallPlayerNBA(name,position,age,stats);
    }

    public static IGoat createGoat(String name, int age, Stats stats){
        return new TennisPlayer(name,age,stats);
    }

}
