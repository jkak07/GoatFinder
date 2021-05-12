package com.goatfinder.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GoatMaker implements IGoatFactory {
   

    public static IGoat createBasketBallPlayer(String name, String position, int age, GoatStats stats){
        return new BasketballPlayer(name,position,age,stats);
    }

    public static IParser parseBasketBallData(String fileName, Map<String, Opinion> opinions){
        return new BasketballParser(fileName,opinions);
    }

    //create a function where you can create a new basketball player from the CSV file
    //more flexible code if elements of code can be re-used
    //factory allows you to hide implementation, name classes

    public static IGoatDisplayer goatResults(GoatAnalyzer analyzedData){
        return new GoatFinder(analyzedData);
    }

    public static IGoat createBasketballPlayer(String[] playerInfo, Map<String, Opinion> goatFields, String[] colNames, Map<String, List<Double>> dataCols) {

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



    @Override
    public IGoat createBasketballPlayer1(String[] playerInfo, Map<String, Opinion> goatFields, String[] colNames, Map<String, List<Double>> dataCols) {

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
