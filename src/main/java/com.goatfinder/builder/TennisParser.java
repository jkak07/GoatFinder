package com.goatfinder.builder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TennisParser {

    final private String fileName;
    final private Map<String, List<Double>> dataCols;
    final private List<TennisPlayer> dataRows;
    final private Map<String,Opinion> goatFields;

    public TennisParser(final String filename,  Map<String,Opinion> userOpinion){
        this.fileName = filename;
        goatFields = userOpinion;
        dataCols = new HashMap<>();
        dataRows = new ArrayList<>();
        read();

    }


    /*read and parse nba file*/
    public void read(){

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String[] colNames = reader.readLine().split(",");
            String line;

            while ((line = reader.readLine()) != null) {
                String[] playerInfo= line.split(",");

                GoatStats playerStats = new GoatStats();
                TennisPlayer.Builder tennisBuilder = new TennisPlayer.Builder();

                for(int i = 0; i < playerInfo.length; i++){
                    String colName = colNames[i];
                    switch(colName){
                        case "name":
                             tennisBuilder.name((playerInfo[i]));
                            break;
                        case "country_name":
                            tennisBuilder.country(playerInfo[i]);
                            break;
                        case "dob":
                            tennisBuilder.age(parseAge(playerInfo[i]));
                            break;
                        case "active":
                            tennisBuilder.active(Boolean.valueOf(playerInfo[i]));
                            break;
                        case "wonLost":
                            playerStats.setPeriod((double)parsePeriod(playerInfo[i]));
                            dataCols.computeIfAbsent(GoatStats.PERIOD, k -> new ArrayList<>()).add(playerStats.getPeriod());
                            break;
                    }

                    storeChosenFields(colNames[i],playerInfo[i],playerStats);
                }
                final TennisPlayer atpPlayer = tennisBuilder.stats(playerStats).build();
                if(!dataRows.contains(atpPlayer)){
                    dataRows.add(atpPlayer);
                }
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    int parseAge(String dateOfBirth){
        LocalDate dob = LocalDate.parse(dateOfBirth);
        Period p = Period.between(dob, LocalDate.now());
    return p.getYears();
    }

     int parsePeriod(String gamesPlayed){
        return parseGamesWon(gamesPlayed) + parseGamesLost(gamesPlayed);
    }

     int parseGamesWon(String gamesWon){
        return Integer.parseInt(gamesWon.substring(0,gamesWon.indexOf('-')));
    }

     int parseGamesLost(String gamesLost){
        return Integer.parseInt(gamesLost.substring(gamesLost.indexOf('-') +1));
    }

     void storeChosenFields(String header, String stat,GoatStats storage ){
        if (goatFields.containsKey(header)) {
            double statValue = (!stat.isEmpty()) ? Double.parseDouble(stat) : 0;
            storage.getStatHolder().put(header, statValue);
            dataCols.computeIfAbsent(header, k -> new ArrayList<>()).add(statValue);
        }
    }

     Map<String,List<Double>> getDataCols(){
        return this.dataCols;
    }

     List<TennisPlayer> getDataRows(){
        return this.dataRows;
    }

     Map<String,Opinion> getGoatOpinions(){
        return this.goatFields;
    }


}