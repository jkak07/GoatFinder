package com.goatfinder.builder ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Component("Goat")
 public class BasketballAnalyzer extends GoatAnalyzer {

     private static final int MINIMUM_POPULATION_SAMPLE_SIZE = 30;


    public BasketballAnalyzer(IParser fileData) {
        super(fileData);
        this.skewTest();
        this.getDataMeasures();
        this.goatCalculator();

    }



    /*public static GoatAnalyzer BasketBallAnalyzer(IParser dataSet){
        return new BasketballAnalyzer(dataSet);}*/

     void goatCalculator() {

        if (dataSet.getDataRows().size() < MINIMUM_POPULATION_SAMPLE_SIZE) {

            System.out.println("Sample is too small to have an accurate test of true goatness");
        } else {

            for (IGoat player : dataSet.getDataRows()) {

                double sum = 0;
                for (String statName : player.getGoatStats().getStatHolder().keySet()) {

                    double statValue = player.getGoatStats().getStatHolder().get(statName);

                    sum = sum + (GoatAnalyzer.normalize(statValue, dataMeans.get(statName), dataStandardDeviations.get(statName))
                            * dataSet.getGoatOpinions().get(statName).getRating());
                }
                sum *= periodMultiplier(player);
                player.getGoatStats().setGoatScore(sum);
            }
        }

    }







}
