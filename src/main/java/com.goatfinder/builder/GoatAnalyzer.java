
package com.goatfinder.builder ;


import java.util.*;

public abstract class GoatAnalyzer  {
    protected static final int BASE_VALUE = 100;
    protected static final double SKEW_LIMIT = 0.5;

    protected final Map<String, Double> dataMeans;
    protected final Map<String, Double> dataStandardDeviations;
    protected final Map<String, Double> dataMedian;
    protected final Map<String, Double> dataSkew;
    protected final Map<String, Double> dataMaximums;
    protected double periodRange;
    protected final IParser dataSet;
    protected List<IGoat> rankedData;
    protected Map<String,Boolean> skewTestResults;


    public GoatAnalyzer(IParser fileData) {
        dataSet = fileData;

        dataMeans = new HashMap<>();
        dataMedian = new HashMap<>();
        dataMaximums = new HashMap<>();
        dataStandardDeviations = new HashMap<>();
        dataSkew = new HashMap<>();
        periodRange = 0;
        skewTestResults = new HashMap<>();

    }

    public void skewTest(){

        for (String statName : dataSet.getDataCols().keySet()){
            List<Double> statValues = dataSet.getDataCols().get(statName);
            dataSkew.put(statName,GoatMath.skew(dataSet.getDataCols().get(statName)));
            dataMaximums.put(statName,GoatMath.skew(dataSet.getDataCols().get(statName)));

            if (dataSkew.get(statName) < -SKEW_LIMIT  && !statName.equals(IParser.PERIOD)) {
                GoatAnalyzer.reflectData(statValues);
                Opinion weighting = dataSet.getGoatOpinions().get(statName);
                dataSet.getGoatOpinions().put(statName, weighting.reflect());
                GoatMath.transform(statValues);
                skewTestResults.put(statName,true);

            }
            else if (dataSkew.get(statName) > SKEW_LIMIT && !statName.equals(IParser.PERIOD)){
                GoatMath.transform(statValues);
                skewTestResults.put(statName,true);
            }
            else{
                skewTestResults.put(statName,false);
            }
        }
        skewDataUpdateRows();
    }


    public void skewDataUpdateRows(){

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


    public void getDataMeasures(){
        for (String statName : dataSet.getDataCols().keySet()){
            dataMeans.put(statName, GoatMath.mean(dataSet.getDataCols().get(statName)));
            dataStandardDeviations.put(statName, GoatMath.standardDeviation(dataSet.getDataCols().get(statName)));
            dataMedian.put(statName, GoatMath.median(dataSet.getDataCols().get(statName)));
            dataSkew.put(statName,GoatMath.skew(dataSet.getDataCols().get(statName))); //take off afterwards
            if(statName.equals(IParser.PERIOD)){
                periodRange = GoatMath.range(dataSet.getDataCols().get(statName));
            }
        }
    }


    public static double normalize(double stat, double mean, double standardDev) {
        return (stat - mean) / standardDev;

    }

    public void goatRanker(){
        rankedData = new ArrayList<>(dataSet.getDataRows());

        Comparator<IGoat> goatScore  = (o1, o2)-> Double.compare(o2.getGoatStats().getGOATScore(),
                o1.getGoatStats().getGOATScore());
        Collections.sort(rankedData, goatScore);

    }

    public void print(int topXPlayers) {

        rankedData.get(0).goatCase();
        rankedData.stream()
                .limit(topXPlayers)
                .forEach((player)-> System.out.println(player));

    }

    public abstract void goatCalculator();

    public void convertResults(){
        double max = rankedData.get(0).getGoatStats().getGOATScore();
        double multiplier = BASE_VALUE/max;
        for(IGoat player : rankedData) {
            double convertedScore = player.getGoatStats().getGOATScore()*multiplier;
            player.getGoatStats().setGOATScore(convertedScore);
        }
    }


    public double periodMultiplier(IGoat goat) {
        if (goat.getGoatStats().getPeriod() > periodRange * 0.8) {
            return 1;
        } else {
            return 0.75;
        }
    }

    public static List<Double> reflectData(List<Double> list){
        double pivot = Collections.max(list);
        for(int i = 0;  i < list.size() ; i++){
            list.set(i,  2 + pivot - list.get(i));
        }
        return list;
    }


    private static class GoatMath {

        private static double mean(List<Double> list) {
            double sum = 0;

            for (double val : list) {
                sum = sum + val;
            }
            return sum / list.size();
        }

        private static double standardDeviation(List<Double> list) {
            double sum = 0;

            for (double val : list) {
                sum = sum + (val - mean(list)) * (val - mean(list));
            }
            return Math.sqrt(sum / (list.size() - 1));
        }

        private static double median(List<Double> list) {
            ArrayList<Double> copy = new ArrayList<>(list);
            Collections.sort(copy);

            double mid;
            if (copy.size() % 2 == 0) {
                mid = (copy.get(copy.size() / 2) + copy.get(copy.size() / 2 - 1)) / 2;
            } else mid = copy.get(copy.size() / 2);

            return mid;
        }

        private static double skew(List<Double> list){
            double sum = 0;
            double factor = 1/((list.size()-1)*(Math.pow(GoatMath.standardDeviation(list),3)));
            for(double val: list){
                sum += (val - mean(list))*(val - mean(list))*(val - mean(list));
            }
            return sum*factor;

        }


        private static void transform(List<Double> list){
            for (int i = 0; i < list.size(); i++) {
                list.set(i, (Math.sqrt(list.get(i))));
            }
        }

        private static double range(List<Double> list){
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

        private static double maximum(List<Double> list){
            double max = list.get(0);

            for (double val : list) {

                if(val > max){
                    max = val;
                }
            }
            return max ;
        }



    }




    public Map<String, Double> getDataMeans() {
        return dataMeans;
    }

    public Map<String, Double> getDataStandardDeviations() {
        return dataStandardDeviations;
    }

    public Map<String, Double> getDataMedian() {
        return dataMedian;
    }

    public Map<String, Double> getDataSkew() {
        return dataSkew;
    }

    public double getPeriodRange() {
        return periodRange;
    }

    public IParser getDataSet() {
        return dataSet;
    }

    public Map<String, Boolean> getSkewTestResults() {
        return skewTestResults;
    }
}
