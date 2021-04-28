package com.goatfinder.builder ;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserNBA implements IParser {

    final private String fileName;
    final private Map<String, List<Double>> dataCols;
    final private List<BasketBallPlayerNBA> dataRows;
    final private Map<String,Opinion> goatFields;

    public ParserNBA(final String filename,  Map<String,Opinion> userOpinion){
        this.fileName = filename;
        goatFields = userOpinion;
        dataCols = new HashMap<>();
        dataRows = new ArrayList<>();
        read();

    }

    /*remove unnecessary string in player names*/
    public static String parseName(String name){
        String parsedName = null;
        if(name.contains("\\")){
            parsedName = name.substring(0,name.indexOf("\\"));}
        return parsedName;
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

                String name = "";
                String position = "";
                int age = 0;
                Stats playerStats = new Stats();

                for(int i = 0; i < playerInfo.length; i++){
                    String colName = colNames[i];
                    switch(colName){
                        case "Player":
                            name = parseName(playerInfo[i]);
                            break;
                        case "Pos":
                            position = playerInfo[i];
                            break;
                        case "Age":
                            age = Integer.parseInt(playerInfo[i]);
                            break;
                        case "G":
                            playerStats.setPeriod(Double.parseDouble(playerInfo[i]));
                            dataCols.computeIfAbsent(PERIOD, k -> new ArrayList<>()).add(playerStats.getPeriod());
                            break;
                    }
                    storeChosenFields(colNames[i],playerInfo[i],playerStats);
                }
                storeGoat(name, position, age, playerStats);
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void storeGoat(String name, String position, int age, Stats playerStats){
        //IGoat nbaPlayer = GoatFactory.createGoat(name, position, age, playerStats);

        final BasketBallPlayerNBA nbaPlayer = new BasketBallPlayerNBA(name, position, age, playerStats);
        if(!dataRows.contains(nbaPlayer)){
            dataRows.add(nbaPlayer);
        }
    }

    private void storeChosenFields(String header, String stat,Stats storage ){
        if (goatFields.containsKey(header)) {
            double statValue = (!stat.isEmpty()) ? Double.parseDouble(stat) : 0;
            storage.getStatHolder().put(header, statValue);
            dataCols.computeIfAbsent(header, k -> new ArrayList<>()).add(statValue);
        }
    }

    public Map<String,List<Double>> getDataCols(){
        return this.dataCols;
    }

    public List<BasketBallPlayerNBA> getDataRows(){
        return this.dataRows;
    }

    public Map<String,Opinion> getGoatOpinions(){
        return this.goatFields;
    }


}

