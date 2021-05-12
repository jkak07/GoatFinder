package com.goatfinder.builder ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@Component("Parser")
public class  BasketballParser implements IParser{

    final private String fileName;
    final private Map<String, List<Double>> dataCols;
    final private List<IGoat> dataRows; //Have a generic problem
    final private Map<String,Opinion> goatFields;
    private IGoatFactory iGoatFactory;


    public BasketballParser(final String filename, Map<String,Opinion> userOpinion ){
        this.fileName = filename;
        goatFields = userOpinion;
        dataCols = new HashMap<>();
        dataRows = new ArrayList<>();
        System.out.println("1st constructor");

    }
    //
    @Autowired
    public BasketballParser(final String filename, Map<String,Opinion> userOpinion, IGoatFactory goatFactory){
        this.fileName = filename;
        goatFields = userOpinion;
        dataCols = new HashMap<>();
        dataRows = new ArrayList<>();
        this.iGoatFactory = goatFactory;
        read();
        System.out.println("2nd constructor");
    }


    @PostConstruct
    private void initialise(){
        System.out.println(getClass().getName() + " constructed");
    }


    public static IParser ParseBasketballData(final String filename, Map<String,Opinion> userOpinion){
        return new BasketballParser(filename,userOpinion);
    }

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

                //IGoat nbaPlayer = GoatMaker.createBasketballPlayer(playerInfo, goatFields, colNames, dataCols);
                IGoat nbaPlayer = iGoatFactory.createBasketballPlayer1(playerInfo, goatFields, colNames, dataCols);

                if(!dataRows.contains(nbaPlayer)){
                    dataRows.add(nbaPlayer);
                }
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Map<String,List<Double>> getDataCols(){
        return new HashMap<>(this.dataCols);
    }

    public List<IGoat> getDataRows(){
        return new ArrayList<>(this.dataRows);
    }

    public Map<String,Opinion> getGoatOpinions(){
        return new HashMap<>(this.goatFields);
    }


}

