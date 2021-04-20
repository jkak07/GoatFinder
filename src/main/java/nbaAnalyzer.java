import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class nbaAnalyzer extends GoatAnalyzer {

    nbaAnalyzer(IParser fileData) {
        super(fileData);
        this.goatCalculator();
        super.goatRanker();
        super.convertResults();
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
                            * player.getGoatStats().getWeightingHolder().get(statName).getRating());
                }
                player.getGoatStats().setGOATScore(sum);
            }
        }

    }

    public void filter(String position){

        Predicate<BasketBallPlayerNBA> positionFilter = player -> player.getPosition().equals(position);

        //rankedData.stream().filter(positionFilter);

    }



}
