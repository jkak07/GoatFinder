/*GOAT = Greatest Of All Time - swimmer, teacher, dev, dancer,
This application consumes data pertinent to a subject matter person and establishes who is the best in that field
The methodology depends on input data and a series of stats which normalises it*/


import java.util.HashMap;
import java.util.Map;


public class GoatRunner {

    public static void runTheGoat( GoatAnalyzer goatsAnalysed, int size) {

        goatsAnalysed.print(size);

    }
    public static Map<String, Opinion> setOpinions(){
        Map<String, Opinion> opinion = new HashMap<>();

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

        return opinion;
}


    public static void main(String[] args) {

        String fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\nba2020.txt";
        IParser nbaData = new ParserNBA(fileName, setOpinions());

        GoatAnalyzer nbaAnalysed = new nbaAnalyzer(nbaData);

        runTheGoat(nbaAnalysed, 20);

        //nbaAnalysed.filter("C");



    }


}
