
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
    }

    public void goatCase(){
        String ageNotion = "";
        if(this.age <30){
            ageNotion = "And I haven't even reached the twilight of my career.";
        } else{
            ageNotion = "Ask any of my peers, they know who I am, been in this business for years!";
        }

        String proof = "Hey it's " + this.name + " and I play the greatest sport known to man.\n" +
                "We play ball, we fight, with nothing but pure heart.\n" +
                "I am clearly the goat of Basketball. \n" + ageNotion;
        System.out.println(proof + "\n\n" + "Goat Report");
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
