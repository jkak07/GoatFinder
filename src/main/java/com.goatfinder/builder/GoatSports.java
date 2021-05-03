package com.goatfinder.builder ;
import java.util.Objects;

 abstract class GoatSports implements IGoat {

    protected String name;
    protected int age;
    protected GoatStats playerStats;

    public String getName(){ return name;}

    public String toString(){
        return this.name + "'s Goatness is: " + this.playerStats.getGoatScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public boolean equals(Object op){

        if(this.getClass() != op.getClass())
            return false;

        BasketballPlayer otherPlayer = (BasketballPlayer) op;

        return this.name.equals(otherPlayer.name) && this.age == otherPlayer.age;
    }

    public GoatStats getGoatStats(){

        return playerStats;
    }


}
