package com.goatfinder.builder ;
import java.util.*;

public abstract class GoatAnalyzer  {

    protected static final double SKEW_LIMIT = 0.5;
    protected static final double LOW_PERIOD_PENALIZER_MULTIPLIER = 0.75;
    protected static final double MIN_PERIOD_PERCENTAGE = 0.8;


    protected final Map<String, Double> dataMeans;
    protected final Map<String, Double> dataStandardDeviations;
    protected final Map<String, Double> dataMedian;
    protected final Map<String, Double> dataSkew;
    protected final Map<String, Double> dataMaximums;
    protected final double periodRange;
    protected final IParser dataSet;
    protected Map<String,Boolean> skewTestResults;


    public GoatAnalyzer(IParser fileData) {
        dataSet = fileData;
        dataMeans = new HashMap<>();
        dataMedian = new HashMap<>();
        dataMaximums = new HashMap<>();
        dataStandardDeviations = new HashMap<>();
        dataSkew = new HashMap<>();
        skewTestResults = new HashMap<>();
        periodRange = GoatMath.range(dataSet.getDataCols().get(GoatStats.PERIOD));
    }

    public static GoatAnalyzer BasketBallAnalyzer(IParser dataSet){
        return new BasketballAnalyzer(dataSet);
    }

     void getDataMeasures(){
        for (String statName : dataSet.getDataCols().keySet()){
            if(!statName.equals(GoatStats.PERIOD)) {
                dataMeans.put(statName, GoatMath.mean(dataSet.getDataCols().get(statName)));
                dataStandardDeviations.put(statName, GoatMath.standardDeviation(dataSet.getDataCols().get(statName)));
                dataMaximums.put(statName, GoatMath.maximum(dataSet.getDataCols().get(statName)));
            }
        }
    }

    static double normalize(double stat, double mean, double standardDev) {
        return (stat - mean) / standardDev;

    }

     abstract void goatCalculator();

     double periodMultiplier(IGoat goat) {
        if (goat.getGoatStats().getPeriod() > periodRange * MIN_PERIOD_PERCENTAGE) {
            return 1;
        } else {
            return LOW_PERIOD_PENALIZER_MULTIPLIER;
        }
    }

     static void reflectData(List<Double> list){
        double pivot = Collections.max(list);
        for(int i = 0;  i < list.size() ; i++){
            list.set(i,  2 + pivot - list.get(i));
        }

    }

     void skewTest(){

        for (String statName : dataSet.getDataCols().keySet()){
            List<Double> statValues = dataSet.getDataCols().get(statName);
            dataSkew.put(statName,GoatMath.skew(statValues));
            dataMaximums.put(statName,GoatMath.maximum(statValues));

            if(!statName.equals(GoatStats.PERIOD)) {

                if (dataSkew.get(statName) < -SKEW_LIMIT) {
                    GoatAnalyzer.reflectData(statValues); // does it change list
                    Opinion weighting = dataSet.getGoatOpinions().get(statName);
                    dataSet.getGoatOpinions().put(statName, weighting.reflect());
                    GoatMath.transform(statValues);
                    skewTestResults.put(statName, true);

                } else if (dataSkew.get(statName) > SKEW_LIMIT) {
                    GoatMath.transform(statValues);
                    skewTestResults.put(statName, true);
                } else {
                    skewTestResults.put(statName, false);
                }
            }
        }
        skewDataUpdateRows();
    }


     void skewDataUpdateRows(){

        for (IGoat player : dataSet.getDataRows()){
            for (String statName : player.getGoatStats().getStatHolder().keySet()){
                if(dataSkew.get(statName) < -SKEW_LIMIT) {
                    double value = player.getGoatStats().getStatHolder().get(statName);
                    double reflectedValue = 2 + dataMaximums.get(statName) - value;
                    player.getGoatStats().getStatHolder().put(statName,Math.sqrt(reflectedValue));
                }
                if(dataSkew.get(statName) > SKEW_LIMIT) {
                    double value = player.getGoatStats().getStatHolder().get(statName);
                    player.getGoatStats().getStatHolder().put(statName,Math.sqrt(value));
                }
            }
        }
    }


    private static class GoatMath {

        static double mean(List<Double> list) {
            double sum = 0;

            for (double val : list) {
                sum = sum + val;
            }
            return sum / list.size();
        }

        static double standardDeviation(List<Double> list) {
            double sum = 0;

            for (double val : list) {
                sum = sum + (val - mean(list)) * (val - mean(list));
            }
            return Math.sqrt(sum / (list.size() - 1));
        }

        static double median(List<Double> list) {
            ArrayList<Double> copy = new ArrayList<>(list);
            Collections.sort(copy);

            double mid;
            if (copy.size() % 2 == 0) {
                mid = (copy.get(copy.size() / 2) + copy.get(copy.size() / 2 - 1)) / 2;
            } else mid = copy.get(copy.size() / 2);

            return mid;
        }

        static double skew( final List<Double> list){
            double sum = 0;
            double factor = 1/((list.size()-1)*(Math.pow(GoatMath.standardDeviation(list),3)));
            //calculate mean once, double factor final
            for(double val: list){

                sum += (val - mean(list))*(val - mean(list))*(val - mean(list));
            }
            return sum*factor;

        }


        static void transform(List<Double> list){
            for (int i = 0; i < list.size(); i++) {
                list.set(i, (Math.sqrt(list.get(i))));
            }
        }

        static double range(List<Double> list){
            double max = 0;
            double min = list.get(0);
            for (double val : list) {

                if(val < min){
                    min = val;
                }
                if(val > max){
                    max = val;
                }
            }
        return max - min;
        }

         static double maximum(List<Double> list){
            double max = list.get(0);

            for (double val : list) {

                if(val > max){
                    max = val;
                }
            }
            return max ;
        }

    }


    Map<String, Double> getDataMeans() {
        return dataMeans;
    }

    Map<String, Double> getDataStandardDeviations() {
        return dataStandardDeviations;
    }

    Map<String, Double> getDataMedian() {
        return dataMedian;
    }

    Map<String, Double> getDataSkew() {
        return dataSkew;
    }

    double getPeriodRange() {
        return periodRange;
    }

    Map<String, Boolean> getSkewTestResults() {
        return skewTestResults;
    }

    public IParser getDataSet(){return dataSet;} //for spring testing


}
