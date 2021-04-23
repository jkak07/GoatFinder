import java.util.*;



public abstract class GoatAnalyzer  {
    protected static final int BASE_VALUE = 100;
    protected static final float SKEW_LIMIT = 0.5f;
    protected final Map<String, Double> dataMeans;
    protected final Map<String, Double> dataStandardDeviations;
    protected final Map<String, Double> dataMedian;
    protected final Map<String, Double> dataSkew;
    protected double periodRange;
    protected final IParser dataSet;
    protected List<IGoat> rankedData;


    public GoatAnalyzer(IParser fileData) {
        dataSet = fileData;
        dataSet.read();
        dataMeans = new HashMap<>();
        dataMedian = new HashMap<>();
        dataStandardDeviations = new HashMap<>();
        dataSkew = new HashMap<>();
        periodRange = 0;

        this.getDataMeasures();

    }

    public void SkewTest(){

        for (String statName : dataSet.getDataCols().keySet()){
            List<Double> statValues = dataSet.getDataCols().get(statName); // will this change the member variable as it passes by value???
            double skew = GoatMath.skew(dataSet.getDataCols().get(statName));

            if(skew < - SKEW_LIMIT){
              GoatMath.reflectData(statValues);
              Opinion weighting = dataSet.getGoatOpinions().get(statName);
              dataSet.getGoatOpinions().put(statName, weighting.reflect());
              GoatMath.logarithmize(statValues);
            }
            if (skew > SKEW_LIMIT){
                GoatMath.logarithmize(statValues);
            }
        }
    }


    private void getDataMeasures(){
        for (String statName : dataSet.getDataCols().keySet()){
            dataMeans.put(statName, GoatMath.mean(dataSet.getDataCols().get(statName)));
            dataStandardDeviations.put(statName, GoatMath.standardDeviation(dataSet.getDataCols().get(statName)));
            dataMedian.put(statName, GoatMath.median(dataSet.getDataCols().get(statName)));
            dataSkew.put(statName, GoatMath.skew(dataSet.getDataCols().get(statName)));

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
        double x = goat.getGoatStats().getPeriod();

        if (goat.getGoatStats().getPeriod() > periodRange * 0.8) {
            return 1;
        } else {
            return 0.75;
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

        private static double skew(List<Double> list){
            double sum = 0;
            double factor = 1/((list.size()-1)*(Math.pow(GoatMath.standardDeviation(list),3)));
            for(double val: list){
                sum += (val - mean(list))*(val - mean(list))*(val - mean(list));
            }
            return sum*factor;

        }

        private static void reflectData(List<Double> list){
            double pivot = Collections.max(list);
            for(int i = 0;  i < list.size() ; i++){
                list.set(i, pivot - list.get(i));
            }
        }

        private static void logarithmize(List<Double> list){
            for (int i = 0; i < list.size(); i++) {
                list.set(i, Math.log(list.get(i)) + 1);
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

    }

    /*public static void main(String args[]){

        ArrayList<Double> list = new ArrayList<Double>();
        list.add(18.0);
        list.add(5.0);
        list.add(8.0);
        list.add(99.0);
        list.add(4.0);
        list.add(5.0);



        double sum = 0;
        double factor = 1/((list.size()-1)*(Math.pow(GoatMath.standardDeviation(list),3)));
        for(double val: list){
            sum += (val - GoatMath.mean(list))*(val - GoatMath.mean(list))*(val - GoatMath.mean(list));
        }
        System.out.println(sum*factor);

        HashMap<String, Opinion> opinion = new HashMap<>();

        opinion.put("MP", Opinion.MEDIUM_POSITIVE);
        opinion.put("3P%", Opinion.LOW_POSITIVE);
        opinion.put("FTA", Opinion.LOW_POSITIVE);
        opinion.put("TRB", Opinion.STRONG_POSITIVE);
        opinion.put("AST", Opinion.STRONG_POSITIVE);
        opinion.put("STL", Opinion.STRONG_POSITIVE);
        opinion.put("BLK", Opinion.LOW_POSITIVE);
        opinion.put("TOV", Opinion.MEDIUM_NEGATIVE);
        opinion.put("PF", Opinion.LOW_NEGATIVE);
        opinion.put("PTS", Opinion.STRONG_POSITIVE);

        String fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\nba2021test1.txt";
        IParser nbaData = new ParserNBA(fileName, opinion);

        GoatAnalyzer nbaAnalysed = new nbaAnalyzer(nbaData);

        nbaAnalysed.dataSkew();

        }*/






}
