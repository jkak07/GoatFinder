import java.util.HashMap;
import java.util.Map;

public class Stats {

    final private Map<String, Double> statHolder;
    final private Map<String, Opinion> weightingHolder;
    private double gamesPlayed;
    private double goatScore;

    public Stats() {
        statHolder = new HashMap<>();
        weightingHolder = new HashMap<>();
    }

    public Map<String, Double> getStatHolder() {

        return statHolder;
    }

    public Map<String, Opinion> getWeightingHolder() {
        return weightingHolder;
    }

    public void setPeriod(Double gamesPlayed){
        this.gamesPlayed = gamesPlayed;
    }

    public void setGOATScore(double score) {
        goatScore = score;
    }

    public double getGOATScore() {
        return goatScore;
    }

    public double getPeriod(){
        return gamesPlayed;
    }
}
