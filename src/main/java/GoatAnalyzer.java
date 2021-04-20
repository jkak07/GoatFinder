import java.util.*;


public abstract class GoatAnalyzer  {
    protected static final int BASE_VALUE = 100;
    protected final Map<String, Double> dataMeans;
    protected final Map<String, Double> dataStandardDeviations;
    protected final Map<String, Double> dataMedian;
    protected final IParser dataSet;
    protected List<? extends IGoat> rankedData = new ArrayList<>();


    public GoatAnalyzer(IParser fileData) {
        dataSet = fileData;
        dataSet.read();

        dataMeans = new HashMap<>();
        this.dataMean();

        dataMedian = new HashMap<>();
        this.dataMedian();

        dataStandardDeviations = new HashMap<>();
        this.dataStandardDeviation();
    }



    public void dataMean() {
        for (String statName : dataSet.getDataCols().keySet()) {
            dataMeans.put(statName, GoatMath.mean(dataSet.getDataCols().get(statName)));
        }
    }

    private void dataStandardDeviation() {
        for (String statName : dataSet.getDataCols().keySet()) {
            dataStandardDeviations.put(statName, GoatMath.standardDeviation(dataSet.getDataCols().get(statName)));
        }
    }

    private void dataMedian() {

        for (String statName : dataSet.getDataCols().keySet()) {
            dataMedian.put(statName, GoatMath.median(dataSet.getDataCols().get(statName)));
        }
    }

    public double normalize(double stat, double mean, double standardDev) {
        return (stat - mean) / standardDev;

    }

    public void goatRanker(){
        rankedData = new ArrayList<>(dataSet.getDataRows());

        Comparator<IGoat> goatScore  = (o1, o2)-> Double.compare(o2.getGoatStats().getGOATScore(),
                o1.getGoatStats().getGOATScore());
        Collections.sort(rankedData, goatScore);

        //Predicate<IGoat> scoresAbove = p -> p.getGoatStats().getGOATScore() < 90;
        //rankedData.removeIf(scoresAbove);

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
    }
}
