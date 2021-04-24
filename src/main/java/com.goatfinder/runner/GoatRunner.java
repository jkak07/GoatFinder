/*GOAT = Greatest Of All Time - swimmer, teacher, dev, dancer,
This application consumes data pertinent to a subject matter person and establishes who is the best in that field
The methodology depends on input data and a series of stats which normalises it*/
package com.goatfinder.runner ;

import com.goatfinder.builder.*;

import java.util.HashMap;
import java.util.Map;


public class GoatRunner {

    public static void runTheGoat(GoatAnalyzer goatsAnalysed, int size) {

        goatsAnalysed.print(size);

    }
    public static Map<String, Opinion> setOpinions(){
        Map<String, Opinion> opinion = new HashMap<>();

        opinion.put("3P%", Opinion.LOW_POSITIVE);
        opinion.put("FTA", Opinion.LOW_POSITIVE);
        opinion.put("TRB", Opinion.STRONG_POSITIVE);
        opinion.put("AST", Opinion.STRONG_POSITIVE);
        opinion.put("STL", Opinion.STRONG_POSITIVE);
        opinion.put("BLK", Opinion.LOW_POSITIVE);
        opinion.put("TOV", Opinion.MEDIUM_NEGATIVE);
        opinion.put("PF", Opinion.LOW_NEGATIVE);
        opinion.put("PTS", Opinion.STRONG_POSITIVE);

        /*opinion.put("grandSlams", Opinion.MEDIUM_POSITIVE);
        opinion.put("tourFinals", Opinion.LOW_POSITIVE);
        opinion.put("altFinals", Opinion.LOW_POSITIVE);
        opinion.put("masters", Opinion.STRONG_POSITIVE);
        opinion.put("olympics", Opinion.STRONG_POSITIVE);
        opinion.put("bigTitles", Opinion.STRONG_POSITIVE);
        opinion.put("titles", Opinion.LOW_POSITIVE);
        opinion.put("weeksAtNo1", Opinion.MEDIUM_NEGATIVE);
        opinion.put("wonLost", Opinion.LOW_NEGATIVE);
        opinion.put("PTS", Opinion.STRONG_POSITIVE);*/

        return opinion;
}


    public static void main(String[] args) {

        String fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\nba2020.txt";
        //String fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\TennisAllTime.csv";
        IParser nbaData = new ParserNBA(fileName, setOpinions());

        GoatAnalyzer nbaAnalysed = new nbaAnalyzer(nbaData);

        runTheGoat(nbaAnalysed, 20);

        //nbaAnalysed.filter("C");



    }


}
