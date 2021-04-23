import java.util.Objects;

public abstract class SportsGoat implements IGoat {

    protected String name;
    protected int age;
    protected Stats playerStats;

    public String getName(){ return name;}

    public String toString(){
        return this.name + "'s Goatness is: " + this.playerStats.getGOATScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public boolean equals(Object op){

        if(this.getClass() != op.getClass())
            return false;

        BasketBallPlayerNBA otherPlayer = (BasketBallPlayerNBA) op;

        return this.name.equals(otherPlayer.name) && this.age == otherPlayer.age;
    }

    public Stats getGoatStats(){

        return playerStats;
    }


}
