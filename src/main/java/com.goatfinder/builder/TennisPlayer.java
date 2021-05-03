package com.goatfinder.builder;

    class TennisPlayer extends GoatSports {

        protected final String country;
        protected final Boolean active;

   /* TennisPlayer(String name, int age ,String country, Boolean active, IStats tennisStats){
        this.age = age;
        this.name = name;
        this.country = country;
        this.active = active;
        this.playerStats = tennisStats;
    }*/


    public void goatCase(){
        String proof = "My name is " + this.name + " and I play a game suited for gentlemen.\n" +
                "I get to travel the world and dismantle opponents with with hand-eye coordination.\n" +
                "They say I am the 'GOAT' and I wouldn't dare disagree. \n";
        System.out.println(proof + "\n\n" + "Goat Report");

    }

    public static class Builder{
        private String name;
        private int age ;
        private GoatStats playerStats ;
        private String country ;
        private Boolean active ;


        public Builder name(String val){
            name = val; return this;
        }
        public Builder age(int val){
            age = val; return this;
        }
        public Builder country(String val){
            country = val; return this;
        }
        public Builder active(Boolean val){
            active = val; return this;
        }
        public Builder stats(GoatStats val){
            playerStats = val; return this;
        }

        public TennisPlayer build(){
            return new TennisPlayer(this);
        }
    }

    private TennisPlayer(Builder builder){
        name = builder.name;
        age = builder.age;
        playerStats = builder.playerStats;
        country = builder.country;
        active = builder.active;
    }



}
