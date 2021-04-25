package com.goatfinder.builder ;

public class nbaAnalyzer extends GoatAnalyzer {

    public nbaAnalyzer(IParser fileData) {
        super(fileData);
        this.skewTest();
        this.getDataMeasures();
        this.goatCalculator();
        this.goatRanker();
        this.convertResults();
    }

    public void goatCalculator() {

        if (dataSet.getDataRows().size() < 30) {

            System.out.println("Sample is too small to have an accurate test of true goatness");
        } else {

            for (IGoat player : dataSet.getDataRows()) {

                double sum = 0;
                for (String statName : player.getGoatStats().getStatHolder().keySet()) {

                    double statValue = player.getGoatStats().getStatHolder().get(statName);
                    sum = sum + (normalize(statValue, dataMeans.get(statName), dataStandardDeviations.get(statName))
                            * super.dataSet.getGoatOpinions().get(statName).getRating());
                }
                sum *= periodMultiplier(player);
                player.getGoatStats().setGOATScore(sum);
            }
        }

    }



    /*public void filter(String filterType){

        Predicate<BasketBallPlayerNBA> positionFilter = player -> player.position().equals(filterType);


        rankedData.stream().filter(positionFilter).forEach((p)-> System.out.println(p));

    }*/



}
