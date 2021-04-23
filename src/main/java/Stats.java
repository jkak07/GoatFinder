import java.util.HashMap;
import java.util.Map;

public class Stats {

    final private Map<String, Double> statHolder;

    private double gamesPlayed;
    private double goatScore;

    public Stats() {
        statHolder = new HashMap<>();

    }

    public Map<String, Double> getStatHolder() {
        return statHolder;
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
