package com.goatfinder.builder ;
final public class BasketBallPlayerNBA extends SportsGoat {

    private final String position;

    BasketBallPlayerNBA(String name, String position, int age, Stats sNBA){
        this.position = position;
        this.age = age;
        this.name = name;
        this.playerStats = sNBA;
    }

    public String position() {
        return position;
    }


    public void goatCase(){
        String ageNotion;

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




}
