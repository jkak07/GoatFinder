package com.goatfinder.builder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

    class GoatFinder implements IGoatDisplayer  {

    static final int BASE_VALUE = 100;
    final List<? extends IGoat> rankedData;

    GoatFinder(GoatAnalyzer analyzedData){
        rankedData = goatRanker(analyzedData.dataSet.getDataRows());
    }

    static List<? extends IGoat> goatRanker(List<? extends IGoat> data){
        Comparator<IGoat> goatScore = (o1, o2) -> Double.compare(o2.getGoatStats().getGoatScore(),
                o1.getGoatStats().getGoatScore());
        Collections.sort(data, goatScore);

        return data;
    }

    void goatScoreFormatter(){
        double max = rankedData.get(0).getGoatStats().getGoatScore();
        double multiplier = BASE_VALUE/max;
        for(IGoat player : rankedData) {
            double convertedScore = player.getGoatStats().getGoatScore()*multiplier;
            player.getGoatStats().setGoatScore(convertedScore);
        }
    }

    public void displayGoats(int topXPlayers) {
        this.goatScoreFormatter();
        rankedData.get(0).goatCase();
        rankedData.stream()
                .limit(topXPlayers)
                .forEach((player)-> System.out.println(player));
    }

    public List<? extends IGoat> getResults(){
        return new ArrayList<>(rankedData);
    }
}
