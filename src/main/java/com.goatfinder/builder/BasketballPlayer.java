package com.goatfinder.builder ;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class BasketballPlayer extends GoatSports {

    private final String position;

    BasketballPlayer(String name, String position, int age, GoatStats sNBA){
        this.position = position;
        this.age = age;
        this.name = name;
        this.playerStats = sNBA;
    }

    String position() {
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

    public IGoat createBasketBallPlayer(String[] playerInfo, Map<String, Opinion> goatFields, String[] colNames, Map<String, List<Double>> dataCols) {

        String name = "";
        String position = "";
        int age = 0;
        GoatStats playerStats = new GoatStats();

        for (int i = 0; i < playerInfo.length; i++) {
            String colName = colNames[i];
            switch (colName) {
                case "Player":
                    name = BasketballParser.parseName(playerInfo[i]);
                    break;
                case "Pos":
                    position = playerInfo[i];
                    break;
                case "Age":
                    age = Integer.parseInt(playerInfo[i]);
                    break;
                case "G":
                    playerStats.setPeriod(Double.parseDouble(playerInfo[i]));
                    dataCols.computeIfAbsent(GoatStats.PERIOD, k -> new ArrayList<>()).add(playerStats.getPeriod());
                    break;
            }

            if (goatFields.containsKey(colName)) {
                double statValue = (!playerInfo[i].isEmpty()) ? Double.parseDouble(playerInfo[i]) : 0;
                playerStats.getStatHolder().put(colName, statValue);
                dataCols.computeIfAbsent(colName, k -> new ArrayList<>()).add(statValue);
            }
        }
        return new BasketballPlayer(name, position, age, playerStats);
    }



}
