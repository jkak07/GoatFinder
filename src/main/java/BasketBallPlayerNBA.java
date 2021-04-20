
import java.util.Map;
import java.util.Objects;

final public class BasketBallPlayerNBA extends SportsGoat {

    private final String position;
    private final Stats playerStats;


    BasketBallPlayerNBA(String name, String position, int age, Stats sNBA){
        this.position = position;
        this.age = age;
        this.name = name;
        this.playerStats = sNBA;
    }

    public String getPosition() {
        return position;
    }

    public String getName(){ return name;}

    public Stats getGoatStats(){
        return playerStats;
    }

    public String toString(){
        return this.name + "'s Goatness is: " + this.playerStats.getGOATScore();
        //return this.name + "'s Goatness is: " + this.playerStats.getStatHolder().get("3P%");
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public boolean equals(Object op){

        //check if class is the same first
        if(this.getClass() != op.getClass())
            return false;

        //typecast before comparison
        BasketBallPlayerNBA otherPlayer = (BasketBallPlayerNBA) op;

        //age and name define player
        return this.name.equals(otherPlayer.name) && this.age == otherPlayer.age;
    }


}
