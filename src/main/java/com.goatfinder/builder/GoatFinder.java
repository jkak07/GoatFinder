package com.goatfinder.builder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component("Finder")
public class GoatFinder implements IGoatDisplayer {

    static final int BASE_VALUE = 100;
    final List<? extends IGoat> rankedData;

    public GoatFinder(GoatAnalyzer analyzedData){
        rankedData = goatRanker(analyzedData.dataSet.getDataRows());
    }

    public static IGoatDisplayer goatResults(GoatAnalyzer analyzedData){
        return new GoatFinder(analyzedData);
    }

    static List<? extends IGoat> goatRanker(List<? extends IGoat> data){
        Comparator<IGoat> goatScore = (o1, o2) -> Double.compare(o2.getGoatStats().getGoatScore(),
                o1.getGoatStats().getGoatScore());
        Collections.sort(data, goatScore);

        return data;
    }

    @PostConstruct //good for logging and classes constructed after some setters are used
    private void initialise(){
        System.out.println(getClass().getName() + " constructed");
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
