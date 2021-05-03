package com.goatfinder.builder ;
import java.util.HashMap;
import java.util.Map;

class GoatStats  {

    static final String PERIOD = "period" ;
    final private Map<String, Double> statHolder;
    private double gamesPlayed;
    private double goatScore;

    public GoatStats() {
        statHolder = new HashMap<>();

    }

    public Map<String, Double> getStatHolder() {
        return statHolder;
    }

    public void setPeriod(Double gamesPlayed){
        this.gamesPlayed = gamesPlayed;
    }

    public void setGoatScore(double score) {
        goatScore = score;
    }

    public double getGoatScore() {
        return goatScore;
    }

    public double getPeriod(){
        return gamesPlayed;
    }
}
