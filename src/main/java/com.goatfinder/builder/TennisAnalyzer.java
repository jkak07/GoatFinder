package com.goatfinder.builder;

import java.util.ArrayList;
import java.util.List;

 class TennisAnalyzer extends GoatAnalyzer {
     public final static int BEST_STAT_POINTS = 10;
     public final static int RARE_STAT_POINTS = 5;
     public final static int AVERAGE_STAT_POINTS = 2;
     public final static int SPECIAL_STAT_COUNT_POINTS = 2;


     public TennisAnalyzer(IParser fileData) {
         super(fileData);
         this.skewTest();
         this.getDataMeasures();
         this.goatCalculator();

     }

     public static GoatAnalyzer tennisAnalyzer(IParser dataSet) {
         return new TennisAnalyzer(dataSet);
     }

     public void goatCalculator() {

         for (IGoat player : dataSet.getDataRows()) {

             double scoreBuilder = 0;
             for (String statName : player.getGoatStats().getStatHolder().keySet()) {

                 double statValue = player.getGoatStats().getStatHolder().get(statName);
                 int count = 0;

                 if (statValue > 0) {
                     if (statValue == dataMaximums.get(statName)) {
                         scoreBuilder = scoreBuilder + BEST_STAT_POINTS;
                         count++;
                     }
                     if (dataMedian.get(statName) == 0) {
                         scoreBuilder = scoreBuilder + RARE_STAT_POINTS;
                         count++;
                     }
                     if (statValue >= dataMeans.get(statName)) {
                         scoreBuilder = scoreBuilder + AVERAGE_STAT_POINTS;
                         count++;
                     }
                     if (statValue < dataMeans.get(statName)) {
                         count--;
                     }
                     scoreBuilder = scoreBuilder + (count * SPECIAL_STAT_COUNT_POINTS * dataSet.getGoatOpinions().get(statName).getRating());

                 }
                 scoreBuilder = scoreBuilder + (GoatAnalyzer.normalize(statValue, dataMeans.get(statName), dataStandardDeviations.get(statName))
                         * dataSet.getGoatOpinions().get(statName).getRating());

             }
             scoreBuilder *= periodMultiplier(player);
             player.getGoatStats().setGoatScore(scoreBuilder);
         }
     }


     public static int partition(int firstValIndex, int lastValIndex, List<Double> list) {

         double pivot = list.get(lastValIndex); // pivot value by which all elements will be compared against
         int i = firstValIndex - 1; //i is index of last element of list of values smaller than pivot

         //iterates values bigger than pivot values, j is 1st element of unordered list remaining
         for (int j = firstValIndex; j < lastValIndex; i++) {

             //if condition met, will iterate over smaller list and consequently larger list too, swap i and j
             if (list.get(j) <= pivot) {
                 i++;
                 double storeJ = list.get(j);
                 list.set(j, list.get(i));
                 list.set(i, storeJ);
             }
         }
         //swap pivot element with remaining spot in list
         double storeI = list.get(i + 1);
         list.set(i + 1, list.get(lastValIndex));
         list.set(lastValIndex, storeI);

         return i + 1;
     }


 }